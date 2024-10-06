package dev.gisela.paddle_tennis_couch_backend.dtos;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testUserDtoDefaultConstructor() {
        UserDto userDto = new UserDto();
        assertNotNull(userDto);
        assertNull(userDto.getId());
        assertNull(userDto.getUsername());
        assertNull(userDto.getPassword());
        assertNull(userDto.getRoles());
        assertNull(userDto.getEmail());
    }

    @Test
    void testUserDtoConstructorWithoutPassword() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        UserDto userDto = new UserDto(1L, "testUser", roles, "test@example.com");

        assertEquals(1L, userDto.getId());
        assertEquals("testUser", userDto.getUsername());
        assertEquals(roles, userDto.getRoles());
        assertEquals("test@example.com", userDto.getEmail());
        assertNull(userDto.getPassword()); 
    }

    @Test
    void testUserDtoConstructorWithPassword() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        UserDto userDto = new UserDto(1L, "testUser", "testPass", roles, "test@example.com");

        assertEquals(1L, userDto.getId());
        assertEquals("testUser", userDto.getUsername());
        assertEquals("testPass", userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testSettersAndGetters() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("testUser");
        userDto.setPassword("testPass");
        userDto.setRoles(roles);
        userDto.setEmail("test@example.com");

        assertEquals(1L, userDto.getId());
        assertEquals("testUser", userDto.getUsername());
        assertEquals("testPass", userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
        assertEquals("test@example.com", userDto.getEmail());
    }

    @Test
    void testNullSafetyForRoles() {
        UserDto userDto = new UserDto(1L, "testUser", "testPass", null, "test@example.com");
        assertNull(userDto.getRoles());
    }
}
