package dev.gisela.paddle_tennis_couch_backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("john_doe", "password123", "john@example.com");
    }

    @Test
    void shouldSetAndGetUsername() {
        user.setUsername("new_username");
        assertThat(user.getUsername(), is("new_username"));
    }

    @Test
    void shouldSetAndGetPassword() {
        user.setPassword("new_password");
        assertThat(user.getPassword(), is("new_password"));
    }

    @Test
    void shouldSetAndGetEmail() {
        user.setEmail("new_email@example.com");
        assertThat(user.getEmail(), is("new_email@example.com"));
    }

    @Test
    void shouldSetAndGetProfile() {
        Profile profile = new Profile("john@example.com", user);
        user.setProfile(profile);
        assertThat(user.getProfile(), is(profile));
    }

    @Test
    void shouldSetAndGetRoles() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(roleAdmin);
        roles.add(roleUser);

        user.setRoles(roles);

        assertThat(user.getRoles(), containsInAnyOrder(roleAdmin, roleUser));
    }

    @Test
    void shouldReturnProfileEmailWhenPresent() {
        Profile profile = new Profile("profile_email@example.com", user);
        user.setProfile(profile);
        assertThat(user.getEmail(), is("profile_email@example.com"));
    }

    @Test
    void shouldReturnNullWhenProfileNotPresent() {
        user.setProfile(null);
        assertThat(user.getEmail(), is(nullValue()));
    }
}
