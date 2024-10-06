package dev.gisela.paddle_tennis_couch_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityUserTest {

    private User user;
    private SecurityUser securityUser;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        securityUser = new SecurityUser(user);
    }

    @Test
    void testGetPassword() {
        when(user.getPassword()).thenReturn("password");
        assertEquals("password", securityUser.getPassword());
    }

    @Test
    void testGetUsername() {
        when(user.getUsername()).thenReturn("testuser");
        assertEquals("testuser", securityUser.getUsername());
    }

    @Test
    void testGetAuthorities() {
        Role role = new Role("ROLE_ADMIN");
        when(user.getRoles()).thenReturn(Set.of(role));

        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void testAccountNonExpired() {
        assertTrue(securityUser.isAccountNonExpired());
    }

    @Test
    void testAccountNonLocked() {
        assertTrue(securityUser.isAccountNonLocked());
    }

    @Test
    void testCredentialsNonExpired() {
        assertTrue(securityUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(securityUser.isEnabled());
    }
}
