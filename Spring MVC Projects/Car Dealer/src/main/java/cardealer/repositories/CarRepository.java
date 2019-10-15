package cardealer.repositories;

import cardealer.domain.entities.Car;
import cardealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByMakeIgnoreCaseOrderByModelAscTravelledDistanceDesc(String make);

    @Query("SELECT c FROM Car c WHERE :part IN ELEMENTS(c.parts)")
    List<Car> findAllContainingGivenPart(@Param("part") Part part);
}
