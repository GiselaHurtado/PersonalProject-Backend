package dev.gisela.paddle_tennis_couch_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.gisela.paddle_tennis_couch_backend.dtos.UserDto;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> userOptional = userService.findUserById(id);
        return userOptional
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers().stream()
            .map(user -> new UserDto(
                user.getId(),  // Incluir el id en el UserDto
                user.getUsername(),
                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()),
                userService.getEmailByUserId(user.getId())
            ))
            .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestHeader("username") String username,
                                               @RequestHeader("password") String password,
                                               @RequestHeader("email") String email) {
        try {
            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
            }

            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setEmail(email);

            userService.registerNewUser(userDto);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User newUser = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        User savedUser = userService.saveUser(newUser, userDto.getRoles(), userDto.getEmail());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        Optional<User> updatedUser = userService.updateUser(id, userDto, userDto.getRoles(), userDto.getEmail());
        return updatedUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
