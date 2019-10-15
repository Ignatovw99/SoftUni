package cardealer.web.controllers;

import cardealer.domain.models.binding.CustomerAddEditBindingModel;
import cardealer.domain.models.service.customers.CustomerFinancialDetailsServiceModel;
import cardealer.domain.models.service.customers.CustomerServiceModel;
import cardealer.domain.models.view.CustomerFinancialDetailsViewModel;
import cardealer.domain.models.view.CustomerViewModel;
import cardealer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView customersPage() {
        return this.view("customers/customers");
    }

    @GetMapping("/add")
    public ModelAndView addCustomer(Model model) {
        if (!model.containsAttribute("customerAddEditBindingModel")) {
            model.addAttribute("customerAddEditBindingModel", new CustomerAddEditBindingModel());
        }
        return this.view("customers/customers-add");
    }

//    @ModelAttribute
//    LocalDateTime initLocalDateTime() {
//        return LocalDateTime.now();
//    }

    @PostMapping("/add")
    public ModelAndView addCustomerConfirm(
            @ModelAttribute @Valid CustomerAddEditBindingModel customerAddEditBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("bindingModel", bindingModel);
//            redirectAttributes.addFlashAttribute("chec", bindingModel);
            modelAndView.addObject("customerAddEditBindingModel", customerAddEditBindingModel);
            return this.view("customers/customers-add", modelAndView);
        }

        this.customerService.addCustomer(customerAddEditBindingModel);

        return this.view("/customers/customers");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id, ModelAndView modelAndView) {
        CustomerServiceModel customerServiceModel = this.customerService.getCustomerById(id);
        CustomerViewModel customerViewModel = null;
        if (customerServiceModel != null) {
            customerViewModel = this.modelMapper.map(customerServiceModel, CustomerViewModel.class);
        }
        modelAndView.addObject("customer", customerViewModel);
        modelAndView.addObject("customerId", id);
        return this.view("customers/customers-edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCustomerConfirm(@PathVariable("id") Long id, @ModelAttribute @Valid CustomerAddEditBindingModel customerAddEditBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("customers/customers-edit");
        }

        this.customerService.editCustomer(id, customerAddEditBindingModel);
        return this.view("customers/customers");
    }

    @GetMapping("/ascending")
    public ModelAndView customersOrderedAscending(ModelAndView model) {
        List<CustomerViewModel> customersViewModels = this.customerService.getAllCustomersInAscendingOrder()
                .stream()
                .map(customerServiceModel -> this.modelMapper.map(customerServiceModel, CustomerViewModel.class))
                .collect(Collectors.toList());

        model.addObject("customers", customersViewModels);
        return this.view("customers/customers-ascending", model);
    }

    @GetMapping("/descending")
    public ModelAndView customersOrderedDescending(ModelAndView model) {
        List<CustomerViewModel> customersViewModels = this.customerService.getAllCustomersInDescendingOrder()
                .stream()
                .map(customerServiceModel -> this.modelMapper.map(customerServiceModel, CustomerViewModel.class))
                .collect(Collectors.toList());

        model.addObject("customers", customersViewModels);
        return this.view("customers/customers-descending", model);
    }

    @GetMapping("/find/id")
    public ModelAndView findCustomerByIdForm() {
        return this.view("customers/customers-find-by-id");
    }

    @PostMapping("/find/id")
    public ModelAndView findCustomerByIdConfirm(@RequestParam Long id) {
        return this.redirect("/customers/" + id);
    }

    @GetMapping("/{id}")
    public ModelAndView customerDetailsById(@PathVariable("id") Long id, ModelAndView model) {
        CustomerFinancialDetailsServiceModel customerServiceModel = this.customerService.getCustomerFinancialDetailsById(id);
        CustomerFinancialDetailsViewModel customer = null;
        if (customerServiceModel != null) {
            customer = this.modelMapper.map(customerServiceModel, CustomerFinancialDetailsViewModel.class);
        }
        model.addObject("customer", customer);
        model.addObject("customerId", id);
        return this.view("customers/customers-details", model);
    }
}
