package dev.gisela.paddle_tennis_couch_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gisela.paddle_tennis_couch_backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
