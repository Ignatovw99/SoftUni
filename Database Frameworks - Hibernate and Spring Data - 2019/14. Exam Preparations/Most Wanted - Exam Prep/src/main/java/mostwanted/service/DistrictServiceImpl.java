package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DistrictServiceImpl implements DistrictService{

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/districts.json";

    private final DistrictRepository districtRepository;

    private final FileUtil fileUtil;

    private final TownRepository townRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, FileUtil fileUtil, TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0L;
    }

    @Override
    public String readDistrictsJsonFile() {
        return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(districtsFileContent, DistrictImportDto[].class))
                .forEach(districtImportDto -> {
                    District district = this.modelMapper.map(districtImportDto, District.class);
                    Town town = this.townRepository.findByName(districtImportDto.getTownName());

                    if (!this.validationUtil.isValid(district) || town == null) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        boolean existsAlreadySuchDistrict = this.districtRepository.existsByName(district.getName());

                        if (existsAlreadySuchDistrict) {
                            sb.append(Constants.DUPLICATE_DATA_MESSAGE)
                                    .append(System.lineSeparator());
                        } else {
                            district.setTown(town);
                            this.districtRepository.saveAndFlush(district);
                            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, district.getClass().getSimpleName(), district.getName()))
                                    .append(System.lineSeparator());
                        }
                    }
                });

        return sb.toString().trim();
    }
}
