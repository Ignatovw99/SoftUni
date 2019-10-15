package cardealer.web.controllers;

import cardealer.domain.models.binding.PartAddEditBindingModel;
import cardealer.domain.models.service.parts.PartModifyServiceModel;
import cardealer.domain.models.service.parts.PartServiceModel;
import cardealer.domain.models.view.PartViewModel;
import cardealer.services.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController {

    private final PartService partService;

    private final ModelMapper modelMapper;

    public PartController(PartService partService, ModelMapper modelMapper) {
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView partsPage() {
        return this.view("parts/parts");
    }

    @GetMapping("/add")
    public ModelAndView addPart(ModelAndView modelAndView) {
        modelAndView.addObject("partAddEditBindingModel", new PartAddEditBindingModel());
        return this.view("parts/parts-add", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addPartConfirm(
            @ModelAttribute @Valid PartAddEditBindingModel partAddEditBindingModel,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("partAddEditBindingModel", partAddEditBindingModel);
            return this.view("parts/parts-add", modelAndView);
        }

        PartModifyServiceModel partModifyServiceModel = this.modelMapper.map(partAddEditBindingModel, PartModifyServiceModel.class);
        this.partService.addPart(partModifyServiceModel);
        return this.view("parts/parts");
    }

    @GetMapping("/all")
    public ModelAndView allParts(ModelAndView modelAndView) {
        List<PartViewModel> partsViewModels = this.partService.getAllParts()
                .stream()
                .map(partServiceModel -> this.modelMapper.map(partServiceModel, PartViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("parts", partsViewModels);
        return this.view("parts/parts-all", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPart(@PathVariable("id") Long id, ModelAndView modelAndView) {
        PartServiceModel partServiceModel = this.partService.getPartById(id);
        PartViewModel partViewModel = null;
        if (partServiceModel != null) {
            partViewModel = this.modelMapper.map(partServiceModel, PartViewModel.class);
        }
        modelAndView.addObject("partAddEditBindingModel", partViewModel);
        modelAndView.addObject("partId", id);
        return this.view("parts/parts-edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPartConfirm(@PathVariable("id") Long id, @ModelAttribute @Valid PartAddEditBindingModel partAddEditBindingModel, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("partId", id);
            return this.view("parts/parts-edit", modelAndView);
        }

        this.partService.editPart(id, partAddEditBindingModel);
        return this.view("parts/parts");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePart(@PathVariable("id") Long id) {
        this.partService.deletePart(id);
        return this.view("parts/parts");
    }
}
