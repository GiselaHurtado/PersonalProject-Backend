package dev.gisela.paddle_tennis_couch_backend.services;

import dev.gisela.paddle_tennis_couch_backend.models.SecurityUser;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JpaUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JpaUserDetailsService jpaUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_ShouldReturnSecurityUser_WhenUserExists() {
      
        User user = new User("john_doe", "password123", "john@example.com");
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

       
        SecurityUser securityUser = (SecurityUser) jpaUserDetailsService.loadUserByUsername("john_doe");

        
        verify(userRepository, times(1)).findByUsername("john_doe"); 
        assertThat(securityUser.getUsername(), is("john_doe"));
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserDoesNotExist() {
      
        when(userRepository.findByUsername("non_existent_user")).thenReturn(Optional.empty());

      
        assertThrows(UsernameNotFoundException.class, () -> {
            jpaUserDetailsService.loadUserByUsername("non_existent_user");
        });
        verify(userRepository, times(1)).findByUsername("non_existent_user");  
    }
}
