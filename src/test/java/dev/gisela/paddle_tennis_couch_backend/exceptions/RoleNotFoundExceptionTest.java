package dev.gisela.paddle_tennis_couch_backend.exceptions;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoleNotFoundExceptionTest {

    @Test
    void constructor_ShouldInitializeMessage() {
        // Act
        RoleNotFoundException exception = new RoleNotFoundException("Role not found");

        // Assert
        assertThat(exception.getMessage(), is("Role not found"));
    }

    @Test
    void constructor_ShouldInitializeMessageAndCause() {
        // Arrange
        Throwable cause = new Throwable("Root cause");

        // Act
        RoleNotFoundException exception = new RoleNotFoundException("Role not found", cause);

        // Assert
        assertThat(exception.getMessage(), is("Role not found"));
        assertThat(exception.getCause(), is(cause));
    }
}
