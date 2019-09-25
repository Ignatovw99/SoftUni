package residentevil.services.implementations;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.Capital;
import residentevil.domain.entities.Virus;
import residentevil.domain.models.service.VirusServiceModel;
import residentevil.domain.models.view.VirusViewModel;
import residentevil.repositories.CapitalRepository;
import residentevil.repositories.VirusRepository;
import residentevil.services.VirusService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;

    private final CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper, Gson gson) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void spreadVirus(VirusServiceModel virusModel) {
        Virus virus = this.modelMapper.map(virusModel, Virus.class);
        Set<Capital> affectedCapitals = new HashSet<>(this.capitalRepository.findAllById(virusModel.getCapitalsIds()));
        virus.setCapitals(affectedCapitals);
        this.virusRepository.saveAndFlush(virus);
    }

    @Override
    public List<VirusViewModel> extractAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(virus -> this.modelMapper.map(virus, VirusViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VirusServiceModel getVirusById(String id) {
        Optional<Virus> virus = this.virusRepository.findById(id);
        if (virus.isEmpty()) {
            return null;
        }
        return this.modelMapper.map(virus.get(), VirusServiceModel.class);
    }

    @Override
    public void editVirus(String id, VirusServiceModel virusServiceModel) {
        boolean doesVirusExist = this.virusRepository.existsById(id);
        if (!doesVirusExist) {
            return;
        }
        Virus editedVirus = this.modelMapper.map(virusServiceModel, Virus.class);
        editedVirus.setId(id);
        Set<Capital> editedAffectedCapitals = new HashSet<>(this.capitalRepository.findAllById(virusServiceModel.getCapitalsIds()));
        editedVirus.setCapitals(editedAffectedCapitals);
        this.virusRepository.saveAndFlush(editedVirus);
    }

    @Override
    public void deleteVirusById(String id) {
        if (this.virusRepository.existsById(id)) {
            this.virusRepository.deleteById(id);
        }
    }

    @Override
    public boolean existsById(String id) {
        return this.virusRepository.existsById(id);
    }

    @Override
    public String getAllVirusesWithAffectedCapitals() {
        List<Virus> viruses = this.virusRepository.findAll();
        return this.gson.toJson(viruses);
//        return "{\"type\": \"FeatureCollection\", \"features\": [{\"type\": \"Feature\", \"properties\": {\"mag\": 5, \"color\": \"#f00\"}, \"geometry\": {\"type\": \"Point\", \"coordinates\": [43.939999, 12.450000]}}]}";
    }
}
