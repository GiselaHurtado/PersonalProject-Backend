package dev.gisela.paddle_tennis_couch_backend.config;

import dev.gisela.paddle_tennis_couch_backend.facades.encryptations.Base64Encoder;
import dev.gisela.paddle_tennis_couch_backend.services.JpaUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SecurityConfigTest {

    @Mock
    private JpaUserDetailsService jpaUserDetailsService;

    @Mock
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void securityFilterChainTest() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(httpSecurity);
        assertNotNull(securityFilterChain, "SecurityFilterChain should not be null");
    }

    @Test
    void passwordEncoderTest() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }

    @Test
    void base64EncoderTest() {
        Base64Encoder base64Encoder = securityConfig.base64Encoder();
        assertNotNull(base64Encoder, "Base64Encoder should not be null");
    }

    @Test
    void corsConfigurationSourceTest() {
        assertNotNull(securityConfig.corsConfigurationSource(), "CorsConfigurationSource should not be null");
    }
}
