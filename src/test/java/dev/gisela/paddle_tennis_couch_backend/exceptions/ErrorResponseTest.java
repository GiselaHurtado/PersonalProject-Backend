package dev.gisela.paddle_tennis_couch_backend.exceptions;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ErrorResponseTest {

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
   
        int status = 400;
        String message = "Bad Request";
        long timestamp = System.currentTimeMillis();

   
        ErrorResponse errorResponse = new ErrorResponse(status, message, timestamp);

     
        assertThat(errorResponse.getStatus(), is(status));
        assertThat(errorResponse.getMessage(), is(message));
        assertThat(errorResponse.getTimestamp(), is(timestamp));
    }

    @Test
    void setters_ShouldUpdateFieldsCorrectly() {
     
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad Request", System.currentTimeMillis());

  
        errorResponse.setStatus(404);
        errorResponse.setMessage("Not Found");
        long newTimestamp = System.currentTimeMillis();
        errorResponse.setTimestamp(newTimestamp);

        
        assertThat(errorResponse.getStatus(), is(404));
        assertThat(errorResponse.getMessage(), is("Not Found"));
        assertThat(errorResponse.getTimestamp(), is(newTimestamp));
    }
}
