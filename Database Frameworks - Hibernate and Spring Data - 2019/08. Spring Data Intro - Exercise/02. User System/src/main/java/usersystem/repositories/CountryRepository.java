package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByName(String name);
}
