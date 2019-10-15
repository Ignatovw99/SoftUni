package cardealer.services;

import cardealer.domain.models.service.cars.CarServiceModel;

import java.util.List;

public interface CarService {

    List<CarServiceModel> getAllCarsByMake(String make);

    CarServiceModel getCarById(Long id);
}
