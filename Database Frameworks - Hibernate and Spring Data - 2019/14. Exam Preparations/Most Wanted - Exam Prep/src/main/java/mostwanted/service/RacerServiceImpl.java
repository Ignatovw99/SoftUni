package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private final RacerRepository racerRepository;

    private final FileUtil fileUtil;

    private final TownRepository townRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, FileUtil fileUtil, TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0L;
    }

    @Override
    public String readRacersJsonFile() {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder sb =  new StringBuilder();

        Arrays.stream(this.gson.fromJson(racersFileContent, RacerImportDto[].class))
                .forEach(racerImportDto -> {
                    Racer racer = this.modelMapper.map(racerImportDto, Racer.class);
                    Town homeTown = this.townRepository.findByName(racerImportDto.getHomeTownName());
                    if (!this.validationUtil.isValid(racer) || homeTown == null) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        boolean existsAlreadySuchRacer = this.racerRepository.existsByName(racer.getName());
                        if (existsAlreadySuchRacer) {
                            sb.append(Constants.DUPLICATE_DATA_MESSAGE)
                                    .append(System.lineSeparator());
                        } else {
                            racer.setHomeTown(homeTown);
                            this.racerRepository.saveAndFlush(racer);
                            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, racer.getClass().getSimpleName(), racer.getName()))
                                    .append(System.lineSeparator());
                        }
                    }
                });

        return sb.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder exportResult = new StringBuilder();
        this.racerRepository.findAllWithCars()
                .forEach(racer -> {
                    exportResult.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator())
                            .append(String.format("Age: %d", racer.getAge())).append(System.lineSeparator())
                            .append("Cars:").append(System.lineSeparator());
                    racer.getCars()
                            .forEach(car ->
                                    exportResult.append(String.format(" %d %s %s %d",car.getId(), car.getBrand(), car.getModel(), car.getYearOfProduction()))
                                            .append(System.lineSeparator())
                            );
                    exportResult.append(System.lineSeparator());
                });
        return exportResult.toString().trim();
    }
}