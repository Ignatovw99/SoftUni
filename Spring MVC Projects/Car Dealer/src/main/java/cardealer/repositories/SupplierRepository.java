package cardealer.repositories;

import cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByIsImporterFalse();

    List<Supplier> findAllByIsImporterTrue();

    boolean existsByName(String name);

    Supplier findByName(String supplierName);
}
