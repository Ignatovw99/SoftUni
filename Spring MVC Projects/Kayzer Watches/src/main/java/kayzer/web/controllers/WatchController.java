package kayzer.web.controllers;

import kayzer.domain.models.binding.WatchCreateBindingModel;
import kayzer.domain.models.service.WatchServiceModel;
import kayzer.domain.models.view.AllWatchesWatchViewModel;
import kayzer.domain.models.view.TopWatchesWatchViewModel;
import kayzer.domain.models.view.WatchDetailsViewModel;
import kayzer.services.WatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:63343")
@RequestMapping("/watches")
public class WatchController {

    private final WatchService watchService;

    private final ModelMapper modelMapper;

    @Autowired
    public WatchController(WatchService watchService, ModelMapper modelMapper) {
        this.watchService = watchService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/top", produces = "application/json")
    public Set<TopWatchesWatchViewModel> topFourWatches(){
        return this.watchService.getTopFourWatchesByViews()
                .stream()
                .map(watchServiceModel -> this.modelMapper.map(watchServiceModel, TopWatchesWatchViewModel.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @GetMapping(value = "/all", produces = "application/json")
    public Set<AllWatchesWatchViewModel> allWatches() {
        return this.watchService.getAllWatches()
                .stream()
                .map(watchServiceModel -> this.modelMapper.map(watchServiceModel, AllWatchesWatchViewModel.class))
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/details", produces = "application/json")
    public WatchDetailsViewModel watchDetails(@RequestParam("id") String id) {
        return this.modelMapper.map(
                this.watchService.getWatchById(id),
                WatchDetailsViewModel.class
        );
    }

    @PostMapping("/add")
    public ResponseEntity createWatch(@ModelAttribute WatchCreateBindingModel watchCreateBindingModel) throws URISyntaxException {
        boolean createdResult = this.watchService.createWatch(
                this.modelMapper.map(watchCreateBindingModel, WatchServiceModel.class)
        );

        return ResponseEntity.created(new URI("/watches/all"))
                .body(createdResult);
    }
}
