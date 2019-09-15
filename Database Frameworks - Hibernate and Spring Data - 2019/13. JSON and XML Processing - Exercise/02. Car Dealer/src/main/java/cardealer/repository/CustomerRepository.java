package cardealer.repository;

import cardealer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate ASC , c.youngDriver")
    List<Customer> findAllOrderByBirthDate();

    @Query("SELECT c FROM Customer c WHERE SIZE(c.purchases) > 0")
    List<Customer> findAllWithMoreThanOnePurchase();
}
