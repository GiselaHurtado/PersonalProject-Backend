package dev.gisela.paddle_tennis_couch_backend.controllers;

import java.util.HashMap;
import java.util.Map;

import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/api/v1")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);  // Logger para registrar eventos importantes
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/login")  // Ahora se maneja con POST
    public ResponseEntity<Map<String, Object>> login() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            logger.info("Attempting to log in user: {}", username);  // Log de intento de login

            // Buscar usuario en el repositorio por nombre de usuario
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            // Crear el objeto de respuesta con datos del usuario
            Map<String, Object> json = new HashMap<>();
            json.put("message", "Logged in successfully");
            json.put("id", user.getId());
            json.put("username", user.getUsername());
            json.put("roles", auth.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).toArray());

            logger.info("User {} logged in successfully", username);  // Log de éxito

            return ResponseEntity.status(HttpStatus.OK).body(json);  // Retornar respuesta exitosa
        } catch (UsernameNotFoundException e) {
            logger.error("Error during login: User not found", e);  // Log de error si el usuario no es encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));  // Responder con 404
        } catch (Exception e) {
            logger.error("Error during login process", e);  // Log de error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred during login"));  // Responder con 500
        }
    }
}
