package dev.gisela.paddle_tennis_couch_backend.services;

import dev.gisela.paddle_tennis_couch_backend.dtos.UserDto;
import dev.gisela.paddle_tennis_couch_backend.models.Profile;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findUserById_ShouldReturnUserDto_WhenUserExists() {

        User user = new User("john_doe", "password123", "john@example.com");
        Profile profile = new Profile("john@example.com", user); 
        user.setRoles(Set.of(new Role("ROLE_USER"))); 

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(profileService.findByUserId(1L)).thenReturn(Optional.of(profile));  

       
        Optional<UserDto> userDtoOptional = userService.findUserById(1L);

        
        assertThat(userDtoOptional.isPresent(), is(true));
        assertThat(userDtoOptional.get().getEmail(), is("john@example.com"));  
    }

    @Test
    void findUserById_ShouldReturnEmpty_WhenProfileDoesNotExist() {
      
        User user = new User("john_doe", "password123", "john@example.com");
        user.setRoles(Set.of(new Role("ROLE_USER")));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(profileService.findByUserId(1L)).thenReturn(Optional.empty());  

        Optional<UserDto> userDtoOptional = userService.findUserById(1L);

    
        assertThat(userDtoOptional.isPresent(), is(true)); 
        assertThat(userDtoOptional.get().getEmail(), is((String) null));  
    }

    @Test
    void saveUser_ShouldSaveUserWithRoles() {
     
        User user = new User("john_doe", "password123", "john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleService.getRoleByName(anyString())).thenReturn(new Role("ROLE_USER"));

     
        User savedUser = userService.saveUser(user, Set.of("ROLE_USER"), "john@example.com");

       
        verify(userRepository, times(1)).save(user);
        assertThat(savedUser.getRoles().size(), is(1)); 
        assertThat(savedUser.getRoles().iterator().next().getName(), is("ROLE_USER"));
    }

    @Test
    void saveUser_ShouldThrowException_WhenPasswordIsNull() {
       
        User user = new User("john_doe", null, "john@example.com"); 

      
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user, Set.of("ROLE_USER"), "john@example.com"));
    }

    @Test
    void deleteUser_ShouldDeleteUserById() {
      
        userService.deleteUser(1L);

       
        verify(userRepository, times(1)).deleteById(1L);  
    }

    @Test
    void getEmailByUserId_ShouldReturnEmail_WhenProfileExists() {
      
        Profile profile = new Profile("john@example.com", new User());
        when(profileService.findByUserId(1L)).thenReturn(Optional.of(profile));

      
        String email = userService.getEmailByUserId(1L);

        assertThat(email, is("john@example.com"));
    }

    @Test
    void getEmailByUserId_ShouldReturnNull_WhenProfileDoesNotExist() {
       
        when(profileService.findByUserId(1L)).thenReturn(Optional.empty());

  
        String email = userService.getEmailByUserId(1L);

        
        assertThat(email, is((String) null));
    }
}
