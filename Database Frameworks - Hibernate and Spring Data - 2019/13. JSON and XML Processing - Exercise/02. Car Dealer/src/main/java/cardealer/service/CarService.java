package cardealer.service;

import cardealer.domain.dto.binding.CarDto;
import cardealer.domain.dto.view.CarViewDto;
import cardealer.domain.dto.view.CarWithPartsViewDto;
import cardealer.domain.entity.Car;

public interface CarService {

    void addCar(CarDto carDto);

    void addPartsToEachCar(PartService partService);

    Car getRandomCar();

    CarViewDto[] getAllCarsByMake(String make);

    CarWithPartsViewDto[] getAllCarsWithParts();
}
