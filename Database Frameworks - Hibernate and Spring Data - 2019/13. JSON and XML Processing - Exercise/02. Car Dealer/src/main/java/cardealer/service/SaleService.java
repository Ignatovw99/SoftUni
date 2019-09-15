package cardealer.service;

import cardealer.domain.dto.view.SaleAppliedDiscountViewDto;

public interface SaleService {

    void generateSalesRandomly(CustomerService customerService, CarService carService);

    SaleAppliedDiscountViewDto[] getAllSalesWithAppliedDiscount();
}
