package cardealer.configuration;

import cardealer.domain.dto.view.CustomerPurchasesViewDto;
import cardealer.domain.dto.view.OrderedCustomerViewDto;
import cardealer.domain.dto.view.SaleAppliedDiscountViewDto;
import cardealer.domain.dto.view.SupplierNonImporterViewDto;
import cardealer.domain.entity.Customer;
import cardealer.domain.entity.Part;
import cardealer.domain.entity.Sale;
import cardealer.domain.entity.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Customer.class, OrderedCustomerViewDto.class)
                .addMappings(mapper -> mapper.map(
                        Customer::getPurchases,
                        OrderedCustomerViewDto::setSales
                ));

        Converter<Set<Part>, Integer> suppliersPartsToCountConverter = new AbstractConverter<Set<Part>, Integer>() {
            @Override
            protected Integer convert(Set<Part> parts) {
                return parts == null
                        ? null
                        : parts.size();
            }
        };

        modelMapper.createTypeMap(Supplier.class, SupplierNonImporterViewDto.class)
                .addMappings(m -> m.using(suppliersPartsToCountConverter)
                        .map(
                                Supplier::getParts,
                                SupplierNonImporterViewDto::setPartsCount
                        ));

        Converter<Set<Sale>, BigDecimal> purchasesToMoneyConverter = new AbstractConverter<Set<Sale>, BigDecimal>() {
            @Override
            protected BigDecimal convert(Set<Sale> sales) {
                return sales == null
                        ? null
                        : sales.stream()
                        .map(sale -> sale.getCar()
                                .getParts()
                                .stream()
                                .map(Part::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
        };

        Converter<Set<Sale>, Integer> salesToCount = new AbstractConverter<Set<Sale>, Integer>() {
            @Override
            protected Integer convert(Set<Sale> sales) {
                return sales == null
                        ? null
                        : sales.size();
            }
        };

        modelMapper.createTypeMap(Customer.class, CustomerPurchasesViewDto.class)
                .addMappings(m -> m.map(
                        Customer::getName,
                        CustomerPurchasesViewDto::setFullName
                ))
                .addMappings(m -> m.using(purchasesToMoneyConverter)
                        .map(Customer::getPurchases,
                        CustomerPurchasesViewDto::setSpentMoney
                ))
                .addMappings(m -> m.using(salesToCount).map(
                        Customer::getPurchases,
                        CustomerPurchasesViewDto::setBoughtCars
                ));

        Converter<Set<Part>, BigDecimal> partsPriceToTotalPriceConverter = new AbstractConverter<Set<Part>, BigDecimal>() {
            @Override
            protected BigDecimal convert(Set<Part> parts) {
                return parts == null
                        ? null
                        : parts
                        .stream()
                        .map(Part::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
        };

        Converter<Sale, BigDecimal> priceWithDiscountConverter = new AbstractConverter<Sale, BigDecimal>() {
            @Override
            protected BigDecimal convert(Sale sale) {
                BigDecimal priceWithoutDiscount = sale.getCar().getParts()
                        .stream()
                        .map(Part::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                return priceWithoutDiscount.subtract(
                        priceWithoutDiscount
                                .multiply(BigDecimal.valueOf(sale.getDiscount())).divide(BigDecimal.valueOf(100), RoundingMode.CEILING)
                );
            }
        };

        modelMapper.createTypeMap(Sale.class, SaleAppliedDiscountViewDto.class)
                .addMappings(m -> m.using(partsPriceToTotalPriceConverter).map(
                        sale -> sale.getCar().getParts(),
                        SaleAppliedDiscountViewDto::setPrice
                ))
                .addMappings(m -> m.using(priceWithDiscountConverter).map(
                        sale -> sale,
                        SaleAppliedDiscountViewDto::setPriceWithDiscount
                ));

        return modelMapper;
    }
}
