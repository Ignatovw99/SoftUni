package cardealer.service;

import cardealer.domain.dto.binding.CustomerDto;
import cardealer.domain.dto.view.CustomerPurchasesViewDto;
import cardealer.domain.dto.view.OrderedCustomerViewDto;
import cardealer.domain.entity.Customer;

public interface CustomerService {

    void registerCustomer(CustomerDto customerDto);

    Customer getRandomCustomer();

    OrderedCustomerViewDto[] getAllCustomersOrderByBirthDate();

    CustomerPurchasesViewDto[] getAllCustomersWithMoreThanOnePurchase();
}
