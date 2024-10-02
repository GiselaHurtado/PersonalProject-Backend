package dev.gisela.paddle_tennis_couch_backend.controllers;

import java.util.HashMap;
import java.util.Map;

import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Map<String, String> json = new HashMap<>();
        json.put("message", "Logged in successfully");
        json.put("id", String.valueOf(user.getId()));
        json.put("username", user.getUsername());
        json.put("roles", auth.getAuthorities().iterator().next().getAuthority());

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
