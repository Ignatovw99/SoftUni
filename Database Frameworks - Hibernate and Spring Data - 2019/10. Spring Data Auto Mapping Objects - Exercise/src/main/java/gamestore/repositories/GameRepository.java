package gamestore.repositories;

import gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    boolean existsByTitle(String title);

    boolean existsByTrailer(String trailer);

    Game findByTitle(String title);
}
