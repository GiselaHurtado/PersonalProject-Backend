package dev.gisela.paddle_tennis_couch_backend.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleRoleException_ShouldReturnBadRequest() {
     
        RoleException ex = new RoleException("Role error");

    
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleRoleException(ex);

       
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().getMessage(), is("Role error: Role error"));
    }

    @Test
    void handleRoleNotFoundException_ShouldReturnNotFound() {
     
        RoleNotFoundException ex = new RoleNotFoundException("Role not found");

        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleRoleNotFoundException(ex);

      
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody().getMessage(), is("Role not found: Role not found"));
    }

    @Test
    void handleAuthenticationException_ShouldReturnUnauthorized() {
       
        AuthenticationException ex = mock(AuthenticationException.class);
        when(ex.getMessage()).thenReturn("Unauthorized");

       
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleAuthenticationException(ex);

       
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
        assertThat(response.getBody().getMessage(), is("Authentication error: Unauthorized"));
    }

    @Test
    void handleGenericException_ShouldReturnInternalServerError() {
      
        Exception ex = new Exception("Unexpected error");

      
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(ex);


        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(response.getBody().getMessage(), is("An unexpected error occurred: Unexpected error"));
    }
}
