package dev.gisela.paddle_tennis_couch_backend.controllers;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.services.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    @Mock
    private RegisterService registerService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        // Datos de prueba
        String username = "testuser";
        String password = "testpass";
        String encodedPassword = "encodedPassword";
        String email = "testuser@example.com";
        String firstName = "Test";
        String lastName = "User";

      
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

       
        User user = new User(username, encodedPassword, email);
        when(registerService.save(any(RegisterDto.class))).thenReturn(user);

       
        ResponseEntity<Map<String, String>> response = registerController.register(username, password, email, firstName, lastName);

      
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

       
        Map<String, String> body = response.getBody();
        assertEquals("Register successful", body.get("message"));
        assertEquals(username, body.get("username"));
        assertEquals(email, body.get("email"));

   
        verify(registerService, times(1)).save(any(RegisterDto.class));
    }

    @Test
    void testRegisterPasswordEncryption() {
        
        String username = "testuser";
        String password = "testpass";
        String encodedPassword = "encodedPassword";
        String email = "testuser@example.com";
        String firstName = "Test";
        String lastName = "User";

 
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

      
        User user = new User(username, encodedPassword, email);
        when(registerService.save(any(RegisterDto.class))).thenReturn(user);

        
        registerController.register(username, password, email, firstName, lastName);

   
        verify(passwordEncoder, times(1)).encode(password);
    }

    @Test
    void testRegisterFailureDueToEmptyFields() {
 
        when(registerService.save(any(RegisterDto.class))).thenThrow(new IllegalArgumentException("Invalid data"));

        try {
       
            registerController.register("", "", "", "", "");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid data", e.getMessage());
        }

   
        verify(registerService, times(1)).save(any(RegisterDto.class));
    }
}
