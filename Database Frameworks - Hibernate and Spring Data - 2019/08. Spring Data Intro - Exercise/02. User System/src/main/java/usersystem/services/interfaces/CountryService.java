package usersystem.services.interfaces;

import usersystem.entities.Country;

public interface CountryService {

    Long getCountriesCount();

    void seedCountriesTable();

    void addNewCountryToDatabase(Country country);

    Country getCountryByName(String name);
}
