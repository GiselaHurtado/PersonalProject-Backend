package dev.gisela.paddle_tennis_couch_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;
import dev.gisela.paddle_tennis_couch_backend.service.RegisterService;
import dev.gisela.paddle_tennis_couch_backend.facades.encryptations.Base64Encoder;

@RestController
@RequestMapping("${app.api-endpoint}")
public class RegisterController {

    private final RegisterService registerService;
    private final Base64Encoder base64Encoder;

    public RegisterController(RegisterService registerService, Base64Encoder base64Encoder) {
        this.registerService = registerService;
        this.base64Encoder = base64Encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestHeader("X-Username") String encodedUsername,
            @RequestHeader("X-Email") String encodedEmail,
            @RequestHeader("X-Password") String encodedPassword) {
        try {
            String username = base64Encoder.decode(encodedUsername);
            String email = base64Encoder.decode(encodedEmail);
            String password = base64Encoder.decode(encodedPassword);
            
            System.out.println("Received registration request for: " + username);
            
            RegisterDto newUser = new RegisterDto(username, email, password);
            String result = registerService.registerUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}