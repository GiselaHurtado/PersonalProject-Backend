package dev.gisela.paddle_tennis_couch_backend.services;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;
import dev.gisela.paddle_tennis_couch_backend.exceptions.RoleNotFoundException;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.RoleRepository;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() {
        RegisterDto registerDto = new RegisterDto("testUser", "testPass", "test@example.com", "Test", "User");
        Role userRole = new Role("ROLE_USER");
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPass");

        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPass");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = registerService.save(registerDto);

        assertEquals("testUser", savedUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveRoleNotFound() {
        RegisterDto registerDto = new RegisterDto("testUser", "testPass", "test@example.com", "Test", "User");

        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn("encodedPass");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> registerService.save(registerDto));

        verify(userRepository, never()).save(any(User.class));
    }
}
