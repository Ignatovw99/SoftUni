package judgesystem.repositories;

import judgesystem.domain.entities.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    Strategy findByName(String name);
}
