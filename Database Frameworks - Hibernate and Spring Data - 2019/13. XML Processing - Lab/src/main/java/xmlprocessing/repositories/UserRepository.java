package xmlprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xmlprocessing.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
