package cardealer.services;

import cardealer.domain.models.service.sales.SaleDetailsServiceModel;

import java.util.List;

public interface SaleService {

    SaleDetailsServiceModel getSaleById(Long id);

    List<SaleDetailsServiceModel> getAllSalesWithDiscount();

    List<SaleDetailsServiceModel> getAllSalesWithGivenDiscountPercent(Double percent);
}
