package residentevil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import residentevil.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    User findByEmail(String email);
}
