package usersystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.entities.Country;
import usersystem.repositories.CountryRepository;
import usersystem.services.interfaces.CountryService;

import javax.transaction.Transactional;
import java.util.HashSet;

import static usersystem.constants.CountryValues.COUNTRIES_COUNT;
import static usersystem.constants.CountryValues.NAMES;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Long getCountriesCount() {
        return this.countryRepository.count();
    }

    @Override
    public void seedCountriesTable() {
        for (int i = 0; i < COUNTRIES_COUNT; i++) {
            Country country = new Country();
            country.setName(NAMES[i]);
            country.setTowns(new HashSet<>());
            this.addNewCountryToDatabase(country);
        }
    }

    @Override
    public void addNewCountryToDatabase(Country country) {
        this.countryRepository.saveAndFlush(country);
    }

    @Override
    public Country getCountryByName(String name) {
        return this.countryRepository.findCountryByName(name);
    }
}
