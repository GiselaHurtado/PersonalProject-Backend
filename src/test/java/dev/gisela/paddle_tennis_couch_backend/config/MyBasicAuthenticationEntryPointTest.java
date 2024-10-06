package dev.gisela.paddle_tennis_couch_backend.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

class MyBasicAuthenticationEntryPointTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authException;

    @Mock
    private PrintWriter writer;

    @InjectMocks
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myBasicAuthenticationEntryPoint.setRealmName("Spring Digital Academy");
    }

    @Test
    void testCommence() throws IOException {
        when(response.getWriter()).thenReturn(writer);

        myBasicAuthenticationEntryPoint.commence(request, response, authException);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(response).setHeader("WWW-Authenticate", "Basic realm=\"Spring Digital Academy\"");
        verify(writer).println("HTTP Status 401 - " + authException.getMessage());
    }

    @Test
    void testAfterPropertiesSet() {
        myBasicAuthenticationEntryPoint.afterPropertiesSet();

        assert myBasicAuthenticationEntryPoint.getRealmName().equals("Spring Digital Academy");
    }
}
