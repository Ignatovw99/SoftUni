package kayzer.repositories;

import kayzer.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    UserRole findByAuthority(String authority);

    List<UserRole> findAllByOrderByPower();
}
