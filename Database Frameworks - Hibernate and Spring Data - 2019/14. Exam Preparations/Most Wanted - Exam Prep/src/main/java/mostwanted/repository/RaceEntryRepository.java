package mostwanted.repository;

import mostwanted.domain.entities.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
}
