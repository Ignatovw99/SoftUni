package cardealer.web.controllers;

import cardealer.domain.models.service.cars.CarServiceModel;
import cardealer.domain.models.view.CarByMakeViewModel;
import cardealer.domain.models.view.CarWithPartsViewModel;
import cardealer.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    //TODO: Add a functionality to add, edit and delete cars

    @GetMapping("")
    public ModelAndView carsPage() {
        return this.view("cars/cars");
    }

    @GetMapping("/find/make")
    public ModelAndView findCarsByMake() {
        return this.view("cars/cars-find-by-make");
    }

    @PostMapping("/find/make")
    public ModelAndView findCarsByMakeConfirm(@RequestParam String make) {
        return this.redirect("/cars/" + make);
    }

    @GetMapping("/{make}")
    public ModelAndView carsByMake(@PathVariable("make") String make, ModelAndView model) {
        List<CarByMakeViewModel> carsByMakeViewModels = this.carService.getAllCarsByMake(make)
                .stream()
                .map(carServiceModel -> this.modelMapper.map(carServiceModel, CarByMakeViewModel.class))
                .collect(Collectors.toList());

        model.addObject("cars", carsByMakeViewModels);
        model.addObject("make", make);
        return this.view("cars/cars-by-make", model);
    }

    @GetMapping("/find/id")
    public ModelAndView findCarById() {
        return this.view("cars/cars-find-by-id");
    }

    @PostMapping("/find/id")
    public ModelAndView findCarByIdConfirm(@RequestParam Long id) {
        return this.redirect("/cars/" + id + "/parts");
    }

    @GetMapping("/{id}/parts")
    public ModelAndView carPartsView(@PathVariable("id") Long id, ModelAndView model) {
        CarServiceModel carServiceModel = this.carService.getCarById(id);
        CarWithPartsViewModel car = null;
        if (carServiceModel != null) {
            car = this.modelMapper.map(carServiceModel, CarWithPartsViewModel.class);
        }
        model.addObject("car", car);
        model.addObject("id", id);
        return this.view("cars/car-with-parts", model);
    }
}
