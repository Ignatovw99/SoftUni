package cardealer.service.impl;

import cardealer.domain.dto.view.SaleAppliedDiscountViewDto;
import cardealer.domain.entity.Sale;
import cardealer.repository.SaleRepository;
import cardealer.service.CarService;
import cardealer.service.CustomerService;
import cardealer.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

import static cardealer.constant.ConstantValues.DISCOUNTS;
import static cardealer.constant.ConstantValues.MAX_SALES_COUNT;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void generateSalesRandomly(CustomerService customerService, CarService carService) {
        Random random = new Random();
        int salesCount = random.nextInt(MAX_SALES_COUNT) + 1;
        for (int i = 0; i < salesCount; i++) {
            Sale sale = new Sale();
            sale.setCar(carService.getRandomCar());
            sale.setCustomer(customerService.getRandomCustomer());
            sale.setDiscount(DISCOUNTS[random.nextInt(DISCOUNTS.length)]);
            this.saleRepository.saveAndFlush(sale);
        }
    }

    @Transactional
    @Override
    public SaleAppliedDiscountViewDto[] getAllSalesWithAppliedDiscount() {
        return this.modelMapper.map(this.saleRepository.findAll(), SaleAppliedDiscountViewDto[].class);
    }
}
