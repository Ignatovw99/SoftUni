package cardealer.services.implementations;

import cardealer.domain.entities.Part;
import cardealer.domain.entities.Sale;
import cardealer.domain.models.service.sales.SaleDetailsServiceModel;
import cardealer.repositories.SaleRepository;
import cardealer.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    private BigDecimal calculatePriceWithoutDiscount(Set<Part> parts) {
        return parts
                .stream()
                .map(Part::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePriceWithDiscount(Set<Part> parts, Double discount) {
        BigDecimal partsPrice = this.calculatePriceWithoutDiscount(parts);

        return partsPrice.subtract(partsPrice.multiply(BigDecimal.valueOf(discount)));
    }

    private void setSalePrices(SaleDetailsServiceModel saleServiceModel, Sale sale) {
        saleServiceModel.setPriceWithDiscount(this.calculatePriceWithDiscount(sale.getCar().getParts(), sale.getDiscount()));
        saleServiceModel.setPriceWithoutDiscount(this.calculatePriceWithoutDiscount(sale.getCar().getParts()));
    }

    @Override
    public SaleDetailsServiceModel getSaleById(Long id) {
        Sale sale = this.saleRepository.findById(id).orElse(null);
        if (sale == null) {
            return null;
        }
        SaleDetailsServiceModel saleServiceModel = this.modelMapper.map(sale, SaleDetailsServiceModel.class);
        this.setSalePrices(saleServiceModel, sale);
        return saleServiceModel;
    }

    @Override
    public List<SaleDetailsServiceModel> getAllSalesWithDiscount() {
        return this.saleRepository.findAllByDiscountGreaterThan((double) 0)
                .stream()
                .map(sale -> {
                    SaleDetailsServiceModel saleServiceModel = this.modelMapper.map(sale, SaleDetailsServiceModel.class);
                    this.setSalePrices(saleServiceModel, sale);
                    return saleServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleDetailsServiceModel> getAllSalesWithGivenDiscountPercent(Double percent) {
        return this.saleRepository.findAllByDiscountEquals(percent)
                .stream()
                .map(sale -> {
                    SaleDetailsServiceModel saleServiceModel = this.modelMapper.map(sale, SaleDetailsServiceModel.class);
                    this.setSalePrices(saleServiceModel, sale);
                    return saleServiceModel;
                })
                .collect(Collectors.toList());
    }
}
