package usersystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.entities.Town;
import usersystem.repositories.TownRepository;
import usersystem.services.interfaces.CountryService;
import usersystem.services.interfaces.TownService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;

import static usersystem.constants.Constants.EMPTY_TOWNS_TABLE;
import static usersystem.constants.CountryValues.COUNTRIES_COUNT;
import static usersystem.constants.CountryValues.NAMES;
import static usersystem.constants.TownValues.TOWNS;
import static usersystem.constants.TownValues.TOWNS_COUNT_PER_COUNTRY;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public Long getTownsCount() {
        return this.townRepository.count();
    }

    @Override
    public void seedTownsTable(CountryService countryService) {
        for (int i = 0; i < COUNTRIES_COUNT; i++) {
            for (int j = 0; j < TOWNS_COUNT_PER_COUNTRY; j++) {
                Town town = new Town();
                town.setName(TOWNS[i][j]);
                town.setCountry(countryService.getCountryByName(NAMES[i]));
                town.setUsersBorn(new HashSet<>());
                town.setUsersCurrentlyLiving(new HashSet<>());
                this.addNewTownToDatabase(town);
            }
        }
    }

    @Override
    public void addNewTownToDatabase(Town town) {
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public Town getRandomTown() {
        long townsCount = this.getTownsCount();
        if (townsCount == 0L) {
            throw new IllegalArgumentException(EMPTY_TOWNS_TABLE);
        }

        Random random = new Random();
        long townId = random.nextInt((int) townsCount) + 1;
        return this.townRepository.findById(townId)
                .orElse(null);
    }
}
