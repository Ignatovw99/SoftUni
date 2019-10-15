package cardealer.services.implementations;

import cardealer.common.Constants;
import cardealer.domain.entities.Customer;
import cardealer.domain.entities.Part;
import cardealer.domain.entities.Sale;
import cardealer.domain.models.binding.CustomerAddEditBindingModel;
import cardealer.domain.models.service.customers.CustomerFinancialDetailsServiceModel;
import cardealer.domain.models.service.customers.CustomerServiceModel;
import cardealer.repositories.CustomerRepository;
import cardealer.repositories.SaleRepository;
import cardealer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    private Boolean checkIfDriverIsExperienced(CustomerServiceModel customerServiceModel) {
        LocalDateTime customerBirthDate = customerServiceModel.getBirthDate();
        int customerAge = Period.between(customerBirthDate.toLocalDate(), LocalDate.now()).getYears();

        return customerAge >= Constants.AGE_OF_EXPERIENCED_DRIVER;
    }

    @Override
    public void addCustomer(CustomerAddEditBindingModel customer) {
        CustomerServiceModel customerServiceModel = this.modelMapper.map(customer, CustomerServiceModel.class);
        customerServiceModel.setIsYoungDriver(!this.checkIfDriverIsExperienced(customerServiceModel));

        this.customerRepository.saveAndFlush(this.modelMapper.map(customerServiceModel, Customer.class));
    }

    @Override
    public void editCustomer(Long id, CustomerAddEditBindingModel customer) {
        CustomerServiceModel customerServiceModel = this.modelMapper.map(customer, CustomerServiceModel.class);
        customerServiceModel.setIsYoungDriver(!this.checkIfDriverIsExperienced(customerServiceModel));
        customerServiceModel.setId(id);

        this.customerRepository.saveAndFlush(this.modelMapper.map(customerServiceModel, Customer.class));
    }

    @Override
    public List<CustomerServiceModel> getAllCustomersInAscendingOrder() {
        return this.customerRepository.findAllOrderedAscending()
                .stream()
                .map(customer -> this.modelMapper.map(customer, CustomerServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerServiceModel> getAllCustomersInDescendingOrder() {
        return this.customerRepository.findAllOrderedDescending()
                .stream()
                .map(customer -> this.modelMapper.map(customer, CustomerServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerFinancialDetailsServiceModel getCustomerFinancialDetailsById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }

        CustomerFinancialDetailsServiceModel customerWithPurchases = this.modelMapper.map(customer, CustomerFinancialDetailsServiceModel.class);
        List<Sale> allCustomerSales = this.saleRepository.findAllByCustomer(customer);
        customerWithPurchases.setBoughtCars(allCustomerSales.size());
        customerWithPurchases.setTotalSpentMoney(this.calculateTotalSpentMoney(allCustomerSales));
        return customerWithPurchases;
    }

    @Override
    public CustomerServiceModel getCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return this.modelMapper.map(customer, CustomerServiceModel.class);
    }

    private BigDecimal calculateTotalSpentMoney(List<Sale> allCustomerSales) {
        List<BigDecimal> salesPrices = allCustomerSales
                .stream()
                .map(sale -> {
                    Set<Part> currentCarParts = sale.getCar().getParts();
                    Object[] wrapperResult = new Object[] { BigDecimal.valueOf(0) };
                    currentCarParts.forEach(part ->
                        wrapperResult[0] = new BigDecimal(wrapperResult[0].toString()).add(part.getPrice())
                    );

                    return new BigDecimal(wrapperResult[0].toString());
                })
                .collect(Collectors.toList());

        return salesPrices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
