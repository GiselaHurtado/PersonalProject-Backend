package dev.gisela.paddle_tennis_couch_backend.googleCalendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleOAuthService {

    private static final String APPLICATION_NAME = "My Calendar App";
    // Usa JacksonFactory sin getDefaultInstance
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens"; // Carpeta para almacenar los tokens de acceso

    // Scope necesario para acceder al calendario de Google
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";  // Ruta al archivo de credenciales

    /**
     * Cargar las credenciales de cliente de Google desde el archivo.
     *
     * @param HTTP_TRANSPORT El transporte de HTTP a usar (NetHttpTransport)
     * @return Credenciales OAuth 2.0 del usuario.
     * @throws IOException si ocurre un error de E/S
     */
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Cargar el archivo credentials.json de la carpeta resources
        InputStream in = GoogleOAuthService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Archivo de credenciales no encontrado: " + CREDENTIALS_FILE_PATH);
        }

        // Convertir el archivo JSON en un GoogleClientSecrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Construir el flujo de autorización para manejar la autenticación OAuth 2.0
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")  // Solicita un token de acceso de larga duración
                .build();

        // Autoriza al usuario utilizando el flujo de autorización
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Inicializa el servicio de Google Calendar utilizando las credenciales.
     *
     * @return Un cliente autorizado de Google Calendar
     * @throws GeneralSecurityException si ocurre un error de seguridad
     * @throws IOException si ocurre un error de E/S
     */
    public Calendar getCalendarService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credentials = getCredentials(HTTP_TRANSPORT);

        // Construir el cliente de la API de Google Calendar utilizando las credenciales autorizadas
        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
