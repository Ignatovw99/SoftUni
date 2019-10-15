package cardealer.repositories;

import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    List<Part> findAllBySupplier(Supplier supplier);
}
