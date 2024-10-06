package dev.gisela.paddle_tennis_couch_backend.facades.encryptations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64EncoderTest {

    @Test
    void testEncode() {
        Base64Encoder base64Encoder = new Base64Encoder();
        String data = "testData";
        String encodedData = base64Encoder.encode(data);

        assertNotNull(encodedData);
        assertEquals("dGVzdERhdGE=", encodedData); 
    }

    @Test
    void testDecode() {
        Base64Encoder base64Encoder = new Base64Encoder();
        String encodedData = "dGVzdERhdGE=";
        String decodedData = base64Encoder.decode(encodedData);

        assertNotNull(decodedData);
        assertEquals("testData", decodedData); 
    }

    @Test
    void testEncodeAndDecode() {
        Base64Encoder base64Encoder = new Base64Encoder();
        String data = "anotherTest";

        String encodedData = base64Encoder.encode(data);
        String decodedData = base64Encoder.decode(encodedData);

        assertEquals(data, decodedData); 
    }
}
