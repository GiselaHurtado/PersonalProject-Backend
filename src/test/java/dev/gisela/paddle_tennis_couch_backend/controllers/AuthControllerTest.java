package dev.gisela.paddle_tennis_couch_backend.controllers;

import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testLoginSuccess() {
        String username = "testUser";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(authentication.getAuthorities()).thenReturn(Set.of());

        ResponseEntity<Map<String, Object>> response = authController.login();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Logged in successfully", response.getBody().get("message"));
        assertEquals(username, response.getBody().get("username"));
        assertEquals(1L, response.getBody().get("id"));
    }

    @Test
    void testLoginUserNotFound() {
        String username = "unknownUser";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = authController.login();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found: unknownUser", response.getBody().get("error"));
    }

    @Test
    void testLoginInternalServerError() {
        String username = "testUser";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<Map<String, Object>> response = authController.login();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred during login", response.getBody().get("error"));
    }
}
