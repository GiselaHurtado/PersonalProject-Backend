package dev.gisela.paddle_tennis_couch_backend.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDtoTest {

    @Test
    void testRegisterDtoConstructor() {
        RegisterDto registerDto = new RegisterDto("testUser", "testPass", "test@example.com", "Test", "User");

        assertEquals("testUser", registerDto.getUsername());
        assertEquals("testPass", registerDto.getPassword());
        assertEquals("test@example.com", registerDto.getEmail());
        assertEquals("Test", registerDto.getFirstName());
        assertEquals("User", registerDto.getLastName());
    }

    @Test
    void testSettersAndGetters() {
        RegisterDto registerDto = new RegisterDto("testUser", "testPass", "test@example.com", "Test", "User");

        registerDto.setUsername("newUser");
        registerDto.setPassword("newPass");
        registerDto.setEmail("new@example.com");
        registerDto.setFirstName("New");
        registerDto.setLastName("Person");

        assertEquals("newUser", registerDto.getUsername());
        assertEquals("newPass", registerDto.getPassword());
        assertEquals("new@example.com", registerDto.getEmail());
        assertEquals("New", registerDto.getFirstName());
        assertEquals("Person", registerDto.getLastName());
    }

    @Test
    void testNullSafety() {
        RegisterDto registerDto = new RegisterDto(null, null, null, null, null);

        assertNull(registerDto.getUsername());
        assertNull(registerDto.getPassword());
        assertNull(registerDto.getEmail());
        assertNull(registerDto.getFirstName());
        assertNull(registerDto.getLastName());
    }
    
    @Test
    void testEmptyFields() {
        RegisterDto registerDto = new RegisterDto("", "", "", "", "");

        assertEquals("", registerDto.getUsername());
        assertEquals("", registerDto.getPassword());
        assertEquals("", registerDto.getEmail());
        assertEquals("", registerDto.getFirstName());
        assertEquals("", registerDto.getLastName());
    }
}
