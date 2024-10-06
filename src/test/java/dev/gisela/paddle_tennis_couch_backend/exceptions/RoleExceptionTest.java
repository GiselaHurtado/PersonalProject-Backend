package dev.gisela.paddle_tennis_couch_backend.exceptions;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RoleExceptionTest {

    @Test
    void shouldReturnCorrectMessageAndCause() {
        // Arrange
        Throwable cause = new Throwable("Underlying cause");

        // Act
        RoleException exception = new RoleException("Exception message", cause);

        
        assertThat(exception.getMessage(), equalTo("Exception message"));
        assertThat(exception.getCause(), is(cause));
    }
}
