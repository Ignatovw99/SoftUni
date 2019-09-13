package usersystem.services.interfaces;

import usersystem.entities.Town;

public interface TownService {

    Long getTownsCount();

    void seedTownsTable(CountryService countryService);

    void addNewTownToDatabase(Town town);

    Town getRandomTown();
}
