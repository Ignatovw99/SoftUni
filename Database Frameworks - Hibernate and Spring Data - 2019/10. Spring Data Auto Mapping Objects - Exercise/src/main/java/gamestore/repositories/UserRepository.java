package gamestore.repositories;

import gamestore.domain.entities.Game;
import gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findAllByGamesContaining(Game game);
}
