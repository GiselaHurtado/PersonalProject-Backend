package dev.gisela.paddle_tennis_couch_backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.gisela.paddle_tennis_couch_backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
