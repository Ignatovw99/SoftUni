package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Constraint;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;

@Service
public class TownServiceImpl implements TownService{

    private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/towns.json";

    private final TownRepository townRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() != 0L;
    }

    @Override
    public String readTownsJsonFile() {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(townsFileContent, TownImportDto[].class))
                .forEach(townImportDto -> {
                    Town town = this.modelMapper.map(townImportDto, Town.class);
                    if (!this.validationUtil.isValid(town)) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        boolean existsAlreadySuchTown = this.townRepository.existsByName(town.getName());
                        if (existsAlreadySuchTown) {
                            sb.append(Constants.DUPLICATE_DATA_MESSAGE)
                                    .append(System.lineSeparator());
                        } else {
                            this.townRepository.saveAndFlush(town);
                            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, town.getClass().getSimpleName(), town.getName()))
                                    .append(System.lineSeparator());
                        }
                    }
                });

        return sb.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        StringBuilder exportResult = new StringBuilder();
        this.townRepository.findAllWithRacers()
                .forEach(town ->
                        exportResult.append(String.format("Name: %s", town.getName()))
                                .append(System.lineSeparator())
                                .append(String.format("Racers: %d", town.getRacers().size())).append(System.lineSeparator())
                                .append(System.lineSeparator())
                );
        return exportResult.toString().trim();
    }
}