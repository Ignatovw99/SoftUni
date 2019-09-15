package cardealer.web.controller;

import cardealer.domain.dto.binding.CarDto;
import cardealer.domain.dto.binding.CustomerDto;
import cardealer.domain.dto.binding.PartDto;
import cardealer.domain.dto.binding.SupplierDto;
import cardealer.service.*;
import cardealer.util.FileUtil;
import cardealer.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

import static cardealer.constant.JsonFilesPaths.*;

@Controller
public class CarDealerController implements CommandLineRunner {

    private final SupplierService supplierService;

    private final PartService partService;

    private final CarService carService;

    private final CustomerService customerService;

    private final SaleService saleService;

    private final JsonParser jsonParser;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, JsonParser jsonParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... args) throws Exception {
        // When you run the app for the first time, comment everything except the seed database method
        // When you executing the queries methods, comment the seedDatabase method

        this.seedDatabase();
        this.getAllCustomersOrderedByBirthDate();
        this.getAllCarsByMake();
        this.getAllLocalSuppliers();
        this.getCarsWithParts();
        this.getTotalSalesByCustomer();
        this.getAllSalesWithAppliedDiscount();
    }

    private void getAllCustomersOrderedByBirthDate() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.customerService.getAllCustomersOrderByBirthDate()),
                JSON_RESOURCES_PATH.concat(JSON_ORDERED_CUSTOMERS_PATH)
        );
    }

    private void getAllCarsByMake() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.carService.getAllCarsByMake("Toyota")),
                JSON_RESOURCES_PATH.concat(JSON_TOYOTA_CARS_PATH)
        );
    }

    private void getAllLocalSuppliers() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.supplierService.getAllLocalSuppliers()),
                JSON_RESOURCES_PATH.concat(JSON_LOCAL_SUPPLIER_PATH)
        );
    }

    private void getCarsWithParts() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.carService.getAllCarsWithParts()),
                JSON_RESOURCES_PATH.concat(JSON_CARS_AND_PARTS_PATH)
        );
    }

    private void getTotalSalesByCustomer() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.customerService.getAllCustomersWithMoreThanOnePurchase()),
                JSON_RESOURCES_PATH.concat(JSON_CUSTOMERS_TOTAL_SALES_PATH)
        );
    }

    private void getAllSalesWithAppliedDiscount() {
        FileUtil.writeToFile(
                this.jsonParser.parseEntityToJson(this.saleService.getAllSalesWithAppliedDiscount()),
                JSON_RESOURCES_PATH.concat(JSON_SALES_DISCOUNTS_PATH)
        );
    }


    private void seedDatabase() {
        SupplierDto[] supplierDtos = this.jsonParser.parseEntityFromJson(
                FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_SUPPLIERS_PATH)),
                SupplierDto[].class
        );

        Arrays.stream(supplierDtos)
                .forEach(this.supplierService::registerSupplier);

        PartDto[] partDtos = this.jsonParser.parseEntityFromJson(
                FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_PARTS_PATH)),
                PartDto[].class
        );

        Arrays.stream(partDtos)
                .forEach(this.partService::addPart);

        this.partService.setSupplierToEachPart(this.supplierService);

        CarDto[] carDtos = this.jsonParser.parseEntityFromJson(
                FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_CARS_PATH)),
                CarDto[].class
        );

        Arrays.stream(carDtos)
                .forEach(this.carService::addCar);

        this.carService.addPartsToEachCar(this.partService); //Adding parts to each car takes long time, because many parts are generated randomly for each car.   }

        CustomerDto[] customerDtos = this.jsonParser.parseEntityFromJson(
                FileUtil.readFile(JSON_RESOURCES_PATH.concat(JSON_CUSTOMERS_PATH)),
                CustomerDto[].class
        );

        Arrays.stream(customerDtos)
                .forEach(this.customerService::registerCustomer);

        this.saleService.generateSalesRandomly(this.customerService, this.carService);

    }
}