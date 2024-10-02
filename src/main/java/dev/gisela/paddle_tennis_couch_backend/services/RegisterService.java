package dev.gisela.paddle_tennis_couch_backend.services;

import java.util.Collections;

import dev.gisela.paddle_tennis_couch_backend.dtos.RegisterDto;

import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.models.User;
import dev.gisela.paddle_tennis_couch_backend.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.gisela.paddle_tennis_couch_backend.exceptions.RoleNotFoundException;
import dev.gisela.paddle_tennis_couch_backend.repositories.RoleRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

   

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User save(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: ROLE_USER"));
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        userRepository.save(user);

    
        return user;
    }
}
