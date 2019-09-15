package cardealer.service.impl;

import cardealer.domain.dto.binding.CustomerDto;
import cardealer.domain.dto.view.CustomerPurchasesViewDto;
import cardealer.domain.dto.view.OrderedCustomerViewDto;
import cardealer.domain.entity.Customer;
import cardealer.repository.CustomerRepository;
import cardealer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Transactional
@Service
public class CustomerServiceDto implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    public CustomerServiceDto(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerCustomer(CustomerDto customerDto) {
        Customer customer = this.modelMapper.map(customerDto, Customer.class);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        long customersCount = this.customerRepository.count();
        long customerId = random.nextInt((int) customersCount) + 1;
        return this.customerRepository.findById(customerId)
                .orElse(null);
    }

    @Override
    public OrderedCustomerViewDto[] getAllCustomersOrderByBirthDate() {
        return this.modelMapper.map(this.customerRepository.findAllOrderByBirthDate(), OrderedCustomerViewDto[].class);
    }

    @Override
    public CustomerPurchasesViewDto[] getAllCustomersWithMoreThanOnePurchase() {
        return this.modelMapper.map(this.customerRepository.findAllWithMoreThanOnePurchase(), CustomerPurchasesViewDto[].class);
    }
}
