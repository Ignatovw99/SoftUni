package cardealer.services;

import cardealer.domain.models.binding.CustomerAddEditBindingModel;
import cardealer.domain.models.service.customers.CustomerFinancialDetailsServiceModel;
import cardealer.domain.models.service.customers.CustomerServiceModel;

import java.util.List;

public interface CustomerService {

    void addCustomer(CustomerAddEditBindingModel customer);

    void editCustomer(Long id, CustomerAddEditBindingModel customer);

    List<CustomerServiceModel> getAllCustomersInAscendingOrder();

    List<CustomerServiceModel> getAllCustomersInDescendingOrder();

    CustomerFinancialDetailsServiceModel getCustomerFinancialDetailsById(Long id);

    CustomerServiceModel getCustomerById(Long id);
}
