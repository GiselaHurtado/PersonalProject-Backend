package dev.gisela.paddle_tennis_couch_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.gisela.paddle_tennis_couch_backend.facades.encryptations.Base64Encoder;
import dev.gisela.paddle_tennis_couch_backend.service.JpaUserDetailsService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;
    private final MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;
    private final AppProperties appProperties;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService,
            MyBasicAuthenticationEntryPoint basicEntryPoint,
            AppProperties appProperties) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.myBasicAuthenticationEntryPoint = basicEntryPoint;
        this.appProperties = appProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfiguration()))
                .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
                .disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(appProperties.getApiEndpoint() + "/api/v1/register").permitAll()
                .requestMatchers(appProperties.getApiEndpoint() + "/api/v1/test").permitAll()
                .requestMatchers(appProperties.getApiEndpoint() + "/login").authenticated()
                .anyRequest().authenticated()
                )
                .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                .policyDirectives("frame-ancestors 'self'")
                )
                )
                .httpBasic(basic -> basic.authenticationEntryPoint(myBasicAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .userDetailsService(jpaUserDetailsService);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Base64Encoder base64Encoder() {
        return new Base64Encoder();
    }
}
