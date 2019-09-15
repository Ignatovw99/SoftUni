package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;

    private final RacerRepository racerRepository;

    private final CarRepository carRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, RacerRepository racerRepository, CarRepository carRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0L;
    }

    @Override
    public String readRaceEntriesXmlFile() {
        return this.fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() {
        StringBuilder sb = new StringBuilder();

        RaceEntryImportRootDto raceEntryImportRootDto = this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_XML_FILE_PATH);
        raceEntryImportRootDto.getRaceEntryImportDtos()
                .forEach(raceEntryImportDto -> {
                    RaceEntry raceEntry = this.modelMapper.map(raceEntryImportDto, RaceEntry.class);
                    Car car = this.carRepository.findById(raceEntryImportDto.getCarIdNumber())
                            .orElse(null);
                    Racer racer = this.racerRepository.findByName(raceEntryImportDto.getRacerName());

                    if (racer == null || car == null) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        raceEntry.setRace(null);
                        raceEntry.setCar(car);
                        raceEntry.setRacer(racer);
                        this.raceEntryRepository.saveAndFlush(raceEntry);
                        sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, raceEntry.getClass().getSimpleName(), raceEntry.getId()))
                                .append(System.lineSeparator());
                    }
                });

        return sb.toString().trim();
    }
}
