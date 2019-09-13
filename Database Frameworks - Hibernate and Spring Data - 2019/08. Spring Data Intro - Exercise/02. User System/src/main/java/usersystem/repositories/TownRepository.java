package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
}
