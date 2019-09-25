package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.common.annotations.PreAuthenticate;
import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;
import residentevil.domain.models.binding.VirusBindingModel;
import residentevil.domain.models.service.CapitalServiceModel;
import residentevil.domain.models.service.VirusServiceModel;
import residentevil.domain.models.view.VirusViewModel;
import residentevil.repositories.CapitalRepository;
import residentevil.services.VirusService;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

//TODO: IMPLEMENT AN INTERCEPTOR WHICH SETS A TITLE ON EACH PAGE
//TODO: MAKE A FUNCTIONALITY TO RENDER A VIEW WITHOUT GIVING THE VIEW'S NAME TO MODEL AND VIEW -> HINT: MAKE IT WITH REFLECTION
// todo: THE TEMPLATES PATH HAS TO BE THE SAME AS THE NAME OF THE CONTROLLER AND ITS ACTION

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final VirusService virusService;

    private CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(VirusService virusService, CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.virusService = virusService;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    private void addVirusPropertiesToModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("mutations", Mutation.values());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("capitals", this.capitalRepository.findAll());
    }

    private Set<String> getCapitalsIds(Set<CapitalServiceModel> capitals) {
        return capitals.stream()
                .map(CapitalServiceModel::getId)
                .collect(Collectors.toSet());
    }

    @GetMapping("/show")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView showAllViruses(ModelAndView modelAndView) {
        modelAndView.addObject("viruses", this.virusService.extractAllViruses());
        return this.view("viruses/show-viruses", modelAndView);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addVirus(ModelAndView modelAndView, VirusBindingModel virusBindingModel) {
        modelAndView.addObject("virusBindingModel", virusBindingModel);

        this.addVirusPropertiesToModelAndView(modelAndView);

        return this.view("viruses/add-virus", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addVirusConfirm(@Valid @ModelAttribute VirusBindingModel virusBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            this.addVirusPropertiesToModelAndView(modelAndView);

            return this.view("viruses/add-virus", modelAndView);
        }

        this.virusService.spreadVirus(this.modelMapper.map(virusBindingModel, VirusServiceModel.class));
        return this.redirect("/viruses/show");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteVirus(@PathVariable("id") String id, ModelAndView modelAndView) {
        VirusServiceModel virusToDelete = this.virusService.getVirusById(id);

        if (virusToDelete == null) {
            modelAndView.addObject("virusId", id);
            return this.view("viruses/virus-does-not-exist", modelAndView);
        }

        VirusViewModel virusViewModel = this.modelMapper.map(virusToDelete, VirusViewModel.class);
        modelAndView.addObject("virusToDelete", virusViewModel);
        return this.view("viruses/delete-virus", modelAndView);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteVirusConfirm(@ModelAttribute("id") String id, ModelAndView modelAndView) {
        if (!this.virusService.existsById(id)) {
            modelAndView.addObject("virusId", id);
            return this.view("viruses/virus-does-not-exist", modelAndView);
        }
        this.virusService.deleteVirusById(id);
        return this.redirect("/viruses/show");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editVirus(@PathVariable("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("virusId", id);

        VirusServiceModel virusToEdit = this.virusService.getVirusById(id);
        if (virusToEdit == null) {
            return this.view("viruses/virus-does-not-exist", modelAndView);
        }

        VirusBindingModel virusBindingModel = this.modelMapper.map(virusToEdit, VirusBindingModel.class);
        virusBindingModel.setCapitalsIds(this.getCapitalsIds(virusToEdit.getCapitals()));
        this.addVirusPropertiesToModelAndView(modelAndView);

        modelAndView.addObject("virusBindingModel", virusBindingModel);
        return this.view("viruses/edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editVirusConfirm(@PathVariable("id") String id,
                                         @Valid @ModelAttribute VirusBindingModel virusBindingModel,
                                         BindingResult bindingResult,
                                         ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            this.addVirusPropertiesToModelAndView(modelAndView);
            modelAndView.addObject("virusId", id);

            return this.view("viruses/edit-virus", modelAndView);
        }

        this.virusService.editVirus(id, this.modelMapper.map(virusBindingModel, VirusServiceModel.class));
        return this.redirect("/viruses/show");
    }
}
