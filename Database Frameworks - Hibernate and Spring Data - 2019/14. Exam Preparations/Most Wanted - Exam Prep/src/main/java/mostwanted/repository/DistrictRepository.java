package mostwanted.repository;

import mostwanted.domain.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    boolean existsByName(String districtName);

    District findByName(String districtName);
}
