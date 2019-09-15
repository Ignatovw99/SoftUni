package cardealer.service.impl;

import cardealer.domain.dto.binding.CarDto;
import cardealer.domain.dto.view.CarViewDto;
import cardealer.domain.dto.view.CarWithPartsViewDto;
import cardealer.domain.entity.Car;
import cardealer.repository.CarRepository;
import cardealer.service.CarService;
import cardealer.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

import static cardealer.constant.ConstantValues.PARTS_COUNT_LOWER_BOUND;
import static cardealer.constant.ConstantValues.PARTS_COUNT_UPPER_BOUND;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCar(CarDto carDto) {
        Car car = this.modelMapper.map(carDto, Car.class);
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public void addPartsToEachCar(PartService partService) {
        Random random = new Random();
        int partsCount = random.nextInt(PARTS_COUNT_UPPER_BOUND - PARTS_COUNT_LOWER_BOUND) + PARTS_COUNT_LOWER_BOUND;
        this.carRepository.findAll()
                .forEach(car -> {
                    car.setParts(partService.getRandomParts(partsCount));
                    this.carRepository.saveAndFlush(car);
                });
    }

    @Override
    public Car getRandomCar() {
        Random random = new Random();
        long carsCount = this.carRepository.count();
        long carId = random.nextInt((int) carsCount) + 1;
        return this.carRepository.findById(carId)
                .orElse(null);
    }

    @Override
    public CarViewDto[] getAllCarsByMake(String make) {
        return this.modelMapper.map(this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make), CarViewDto[].class);
    }

    @Transactional
    @Override
    public CarWithPartsViewDto[] getAllCarsWithParts() {
        return this.modelMapper.map(this.carRepository.findAll(), CarWithPartsViewDto[].class);
    }
}
