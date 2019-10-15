package cardealer.web.controllers;

import cardealer.domain.models.service.sales.SaleDetailsServiceModel;
import cardealer.domain.models.view.SaleDetailsViewModel;
import cardealer.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController {

    private final SaleService saleService;

    private final ModelMapper modelMapper;

    public SaleController(SaleService saleService, ModelMapper modelMapper) {
        this.saleService = saleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView salesPage() {
        return this.view("sales/sales");
    }

    @GetMapping("/find/id")
    public ModelAndView findSaleById() {
        return this.view("sales/sales-find-by-id");
    }

    @PostMapping("/find/id")
    public ModelAndView findSaleByIdConfirm(@RequestParam Long id) {
        return this.redirect("/sales/" + id);
    }

    @GetMapping("/{id}")
    public ModelAndView saleByIdView(@PathVariable("id") Long id, ModelAndView model) {
        SaleDetailsServiceModel saleServiceModel = this.saleService.getSaleById(id);
        SaleDetailsViewModel saleViewModel = null;
        if (saleServiceModel != null) {
            saleViewModel = this.modelMapper.map(saleServiceModel, SaleDetailsViewModel.class);
        }
        model.addObject("sale", saleViewModel);
        model.addObject("saleId", id);
        return this.view("sales/sale-details", model);
    }

    @GetMapping("/discounted")
    public ModelAndView salesWithDiscount(ModelAndView model) {
        List<SaleDetailsViewModel> salesViewModels = this.saleService.getAllSalesWithDiscount()
                .stream()
                .map(saleServiceModel -> this.modelMapper.map(saleServiceModel, SaleDetailsViewModel.class))
                .collect(Collectors.toList());
        model.addObject("discountedSales", salesViewModels);
        return this.view("sales/sales-discounted", model);
    }

    @GetMapping("/discounted/find/percent")
    public ModelAndView findSaleByDiscountPercent() {
        return this.view("sales/sales-find-by-discount-percent");
    }

    @PostMapping("/discounted/find/percent")
    public ModelAndView findSaleByDiscountPercentConfirm(@RequestParam("percent") Double percent) {
        return this.redirect("/sales/discounted/" + percent);
    }

    @GetMapping("/discounted/{percent}")
    public ModelAndView discountedSaleByPercent(@PathVariable("percent") Double percent, ModelAndView model) {
        List<SaleDetailsViewModel> salesViewModels = this.saleService.getAllSalesWithGivenDiscountPercent(percent)
                .stream()
                .map(saleServiceModel -> this.modelMapper.map(saleServiceModel, SaleDetailsViewModel.class))
                .collect(Collectors.toList());
        model.addObject("discountedSalesWithGivenPercent", salesViewModels);
        return this.view("sales/sales-discounted-with-percent", model);
    }
}
