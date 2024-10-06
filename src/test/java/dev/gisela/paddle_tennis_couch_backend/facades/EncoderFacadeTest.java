package dev.gisela.paddle_tennis_couch_backend.facades;

import dev.gisela.paddle_tennis_couch_backend.facades.encryptations.Base64Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EncoderFacadeTest {

    private PasswordEncoder bCryptPasswordEncoder;
    private Base64Encoder base64Encoder;
    private EncoderFacade encoderFacade;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncoder = mock(PasswordEncoder.class);
        base64Encoder = mock(Base64Encoder.class);
        encoderFacade = new EncoderFacade(bCryptPasswordEncoder, base64Encoder);
    }

    @Test
    void testEncodeWithBcrypt() {
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("bcryptEncoded");

        String result = encoderFacade.encode("bcrypt", "testData");

        assertNotNull(result);
        assertEquals("bcryptEncoded", result); 
        verify(bCryptPasswordEncoder, times(1)).encode("testData");
    }

    @Test
    void testEncodeWithBase64() {
        when(base64Encoder.encode(anyString())).thenReturn("base64Encoded");

        String result = encoderFacade.encode("base64", "testData");

        assertNotNull(result);
        assertEquals("base64Encoded", result); 
        verify(base64Encoder, times(1)).encode("testData");
    }

    @Test
    void testDecodeWithBase64() {
        when(base64Encoder.decode(anyString())).thenReturn("decodedData");

        String result = encoderFacade.decode("base64", "encodedData");

        assertNotNull(result);
        assertEquals("decodedData", result); 
        verify(base64Encoder, times(1)).decode("encodedData");
    }

    @Test
    void testUnsupportedEncodingType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            encoderFacade.encode("unsupportedType", "testData");
        });

        String expectedMessage = "Tipo de codificación no soportado: unsupportedType";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUnsupportedDecodingType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            encoderFacade.decode("unsupportedType", "testData");
        });

        String expectedMessage = "Tipo de decodificación no soportado: unsupportedType";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
