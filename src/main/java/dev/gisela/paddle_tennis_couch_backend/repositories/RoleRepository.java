package dev.gisela.paddle_tennis_couch_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gisela.paddle_tennis_couch_backend.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}
