package cardealer.web.controllers;

import cardealer.domain.models.view.SupplierViewModel;
import cardealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    private final SupplierService supplierService;

    private final ModelMapper modelMapper;

    public SupplierController(SupplierService supplierService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView suppliersPage() {
        return this.view("suppliers/suppliers");
    }

    @GetMapping("/local")
    public ModelAndView localSuppliers(ModelAndView model) {
        List<SupplierViewModel> localSuppliers = this.supplierService.getAllLocalSuppliers()
                .stream()
                .map(supplierServiceModel -> this.modelMapper.map(supplierServiceModel, SupplierViewModel.class))
                .collect(Collectors.toList());
        model.addObject("localSuppliers", localSuppliers);
        return this.view("suppliers/suppliers-local", model);
    }

    @GetMapping("/importer")
    public ModelAndView importerSuppliers(ModelAndView model) {
        List<SupplierViewModel> importerSuppliers = this.supplierService.getAllImporterSuppliers()
                .stream()
                .map(supplierServiceModel -> this.modelMapper.map(supplierServiceModel, SupplierViewModel.class))
                .collect(Collectors.toList());
        model.addObject("importerSuppliers", importerSuppliers);
        return this.view("suppliers/suppliers-importer", model);
    }
}
