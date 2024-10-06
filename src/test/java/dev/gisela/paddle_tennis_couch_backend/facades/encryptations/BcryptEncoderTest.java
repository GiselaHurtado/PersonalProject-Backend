package dev.gisela.paddle_tennis_couch_backend.facades.encryptations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BcryptEncoderTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BcryptEncoder bcryptEncoder;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        bcryptEncoder = new BcryptEncoder(bCryptPasswordEncoder);
    }

    @Test
    void testEncode() {
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String encodedData = bcryptEncoder.encode("testData");

        assertNotNull(encodedData);
        assertEquals("encodedPassword", encodedData);
        verify(bCryptPasswordEncoder, times(1)).encode(anyString());
    }
}
