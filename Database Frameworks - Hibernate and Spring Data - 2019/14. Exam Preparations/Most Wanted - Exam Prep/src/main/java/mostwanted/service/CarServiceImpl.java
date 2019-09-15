package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CarServiceImpl implements CarService{

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/cars.json";

    private final CarRepository carRepository;

    private final RacerRepository racerRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() != 0L;
    }

    @Override
    public String readCarsJsonFile() {
        return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(carsFileContent, CarImportDto[].class))
                .forEach(carImportDto -> {
                    Car car = this.modelMapper.map(carImportDto, Car.class);
                    Racer racer = this.racerRepository.findByName(carImportDto.getRacerName());

                    if (!this.validationUtil.isValid(car) || racer == null) {
                        sb.append(Constants.INCORRECT_DATA_MESSAGE)
                                .append(System.lineSeparator());
                    } else {
                        car.setRacer(racer);
                        this.carRepository.saveAndFlush(car);
                        sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, car.getClass().getSimpleName(),
                                String.format("%s %s @ %d", car.getBrand(), car.getModel(), car.getYearOfProduction())))
                                .append(System.lineSeparator());
                    }
                });

        return sb.toString().trim();
    }
}