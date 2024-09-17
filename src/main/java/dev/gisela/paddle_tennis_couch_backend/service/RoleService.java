package dev.gisela.paddle_tennis_couch_backend.service;

import org.springframework.stereotype.Service;

import dev.gisela.paddle_tennis_couch_backend.exceptions.RoleNotFoundException;
import dev.gisela.paddle_tennis_couch_backend.models.Role;
import dev.gisela.paddle_tennis_couch_backend.repositories.RoleRepository;

@Service
public class RoleService {

    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

}
