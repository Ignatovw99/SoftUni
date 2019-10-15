package cardealer.repositories;

import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByDiscountGreaterThan(Double discount);

    List<Sale> findAllByDiscountEquals(Double discount);

    List<Sale> findAllByCustomer(Customer customer);
}
