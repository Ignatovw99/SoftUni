package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.races.EntryImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/races.xml";

    private final RaceRepository raceRepository;

    private final DistrictRepository districtRepository;

    private final RaceEntryRepository raceEntryRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0L;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_XML_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        RaceImportRootDto raceImportRootDto = this.xmlParser.parseXml(RaceImportRootDto.class, RACES_XML_FILE_PATH);
        raceImportRootDto.getRaceImportDtos()
                .forEach(raceImportDto -> {
                    Race race = this.modelMapper.map(raceImportDto, Race.class);
                    District district = this.districtRepository.findByName(raceImportDto.getDistrictName());
                    race.setDistrict(district);

                    if (!this.validationUtil.isValid(race)) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        List<Integer> raceEntriesIds = raceImportDto.getEntryImportRootDto()
                                .getEntryImportDtos()
                                .stream()
                                .map(EntryImportDto::getId)
                                .collect(Collectors.toList());
                        Set<RaceEntry> raceEntries = new HashSet<>(this.raceEntryRepository.findAllById(raceEntriesIds));

                        raceEntries.forEach(raceEntry -> raceEntry.setRace(race));

                        race.setRaceEntries(raceEntries);
                        this.raceRepository.saveAndFlush(race);
                        sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, race.getClass().getSimpleName(), race.getId()))
                                .append(System.lineSeparator());
                    }
                });

        return sb.toString().trim();
    }
}