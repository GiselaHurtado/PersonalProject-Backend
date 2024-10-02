package dev.gisela.paddle_tennis_couch_backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.services.RegisterService;




@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(
            @RequestHeader String username,
            @RequestHeader String password,
            @RequestHeader String email,
            @RequestHeader String firstName,
            @RequestHeader String lastName)
            {

        RegisterDto newUser = new RegisterDto(username, password, email, firstName, lastName);
        User user = service.save(newUser);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Register successful");
        json.put("username", user.getUsername());
        json.put("email", user.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
