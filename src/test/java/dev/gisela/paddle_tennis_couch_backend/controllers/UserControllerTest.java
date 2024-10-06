package dev.gisela.paddle_tennis_couch_backend.controllers;

import dev.gisela.paddle_tennis_couch_backend.dtos.UserDto;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_ShouldReturnUserDto_WhenUserExists() {
        
        UserDto userDto = new UserDto(1L, "john_doe", Set.of("ROLE_USER"), "john@example.com");
        when(userService.findUserById(1L)).thenReturn(Optional.of(userDto));

      
        ResponseEntity<UserDto> response = userController.getUserById(1L);

      
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(userDto));
    }

    @Test
    void getUserById_ShouldReturnNotFound_WhenUserDoesNotExist() {
       
        when(userService.findUserById(1L)).thenReturn(Optional.empty());

      
        ResponseEntity<UserDto> response = userController.getUserById(1L);

       
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserDtos() {
      
       
        User user = new User("john_doe", "password123", "john@example.com");
        user.setRoles(Set.of(new Role("ROLE_USER"))); // Inicializar los roles del usuario
        UserDto userDto = new UserDto(1L, "john_doe", Set.of("ROLE_USER"), "john@example.com");

        when(userService.findAllUsers()).thenReturn(List.of(user));

 
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();


        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody().get(0).getUsername(), is("john_doe"));
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        // Arrange
        UserDto userDto = new UserDto(null, "john_doe", Set.of("ROLE_USER"), "john@example.com");
        User user = new User("john_doe", "password123", "john@example.com");
        user.setRoles(new HashSet<>(Set.of(new Role("ROLE_USER")))); // Inicializar roles
        when(userService.saveUser(any(User.class), anySet(), anyString())).thenReturn(user);

   
        ResponseEntity<User> response = userController.createUser(userDto);

     
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody().getUsername(), is("john_doe"));
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser_WhenUserExists() {
    
        UserDto userDto = new UserDto(1L, "john_doe", Set.of("ROLE_USER"), "john@example.com");
        User user = new User("john_doe", "password123", "john@example.com");
        user.setRoles(Set.of(new Role("ROLE_USER")));

      
        when(userService.updateUser(eq(1L), any(UserDto.class), anySet(), anyString()))
            .thenReturn(Optional.of(user));  // Devuelve el usuario simulado

      
        ResponseEntity<User> response = userController.updateUser(1L, userDto);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));  // Esperamos un 200 OK
        assertThat(response.getBody().getUsername(), is("john_doe"));  // Verifica el nombre de usuario
    }

    @Test
    void updateUser_ShouldReturnNotFound_WhenUserDoesNotExist() {

        when(userService.updateUser(anyLong(), any(UserDto.class), anySet(), anyString())).thenReturn(Optional.empty());

  
        ResponseEntity<User> response = userController.updateUser(1L, new UserDto());

 
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
     
        ResponseEntity<Void> response = userController.deleteUser(1L);

 
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void registerUser_ShouldReturnBadRequest_WhenEmailIsMissing() {

        ResponseEntity<String> response = userController.registerUser("john_doe", "password123", "");

    
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), is("Email is required"));
    }

    @Test
    void registerUser_ShouldReturnCreated_WhenRegistrationIsSuccessful() {
      
        doNothing().when(userService).registerNewUser(any(UserDto.class));

     
        ResponseEntity<String> response = userController.registerUser("john_doe", "password123", "john@example.com");

    
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is("User registered successfully!"));
    }
}
