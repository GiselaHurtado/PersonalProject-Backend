package dev.gisela.paddle_tennis_couch_backend.googleCalendar;




import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class GoogleCalendarController {

    @Autowired
    private GoogleOAuthService googleOAuthService;

    /**
     * Obtener todos los eventos del calendario del usuario.
     *
     * @return Lista de eventos del calendario.
     */
    @GetMapping("/events")
    public List<Event> getEvents() throws GeneralSecurityException, IOException {
        Calendar service = googleOAuthService.getCalendarService();

        // Llamar a la API de Google Calendar para obtener los eventos
        Events events = service.events().list("primary").execute();
        return events.getItems();
    }

    /**
     * Crear un nuevo evento en el calendario del usuario.
     *
     * @param event Evento que se va a crear
     * @return El evento recién creado.
     */
    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) throws GeneralSecurityException, IOException {
        Calendar service = googleOAuthService.getCalendarService();

        // Insertar un nuevo evento en el calendario
        return service.events().insert("primary", event).execute();
    }

    /**
     * Eliminar un evento del calendario del usuario.
     *
     * @param eventId ID del evento que se va a eliminar.
     */
    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable String eventId) throws GeneralSecurityException, IOException {
        Calendar service = googleOAuthService.getCalendarService();

        // Eliminar el evento por su ID
        service.events().delete("primary", eventId).execute();
    }
}
