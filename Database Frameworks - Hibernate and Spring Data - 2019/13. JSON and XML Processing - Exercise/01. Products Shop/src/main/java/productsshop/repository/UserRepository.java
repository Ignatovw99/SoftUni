package productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsshop.domain.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE SIZE(u.soldProducts) > 0 ORDER BY u.lastName, u.firstName")
    List<User> findAllByAtLeastOneSoldProduct();
}
