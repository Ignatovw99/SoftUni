package mostwanted.repository;

import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    boolean existsByName(String racerName);

    Racer findByName(String racerName);

    @Query("SELECT r FROM Racer r " +
            "WHERE SIZE(r.cars) > 0 AND r.age IS NOT NULL " +
            "ORDER BY SIZE(r.cars) DESC, r.name ASC")
    List<Racer> findAllWithCars();
}