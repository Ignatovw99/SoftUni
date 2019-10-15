package cardealer.services.implementations;

import cardealer.domain.entities.Car;
import cardealer.domain.models.service.cars.CarServiceModel;
import cardealer.domain.models.service.parts.PartServiceModel;
import cardealer.repositories.CarRepository;
import cardealer.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarServiceModel> getAllCarsByMake(String make) {
        return this.carRepository.findAllByMakeIgnoreCaseOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> this.modelMapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarServiceModel getCarById(Long id) {
        Car car = this.carRepository.findById(id).orElse(null);
        if (car == null) {
            return null;
        }

        CarServiceModel carServiceModel = this.modelMapper.map(car, CarServiceModel.class);
        carServiceModel.setPrice(this.calculateCarPrice(carServiceModel.getParts()));
        return carServiceModel;
    }

    private BigDecimal calculateCarPrice(Set<PartServiceModel> parts) {
        BigDecimal price = BigDecimal.ZERO;

        for (PartServiceModel part : parts) {
            price = price.add(part.getPrice());
        }

        return price;
    }
}
