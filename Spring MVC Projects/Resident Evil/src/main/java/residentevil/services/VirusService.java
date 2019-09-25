package residentevil.services;

import residentevil.domain.models.service.VirusServiceModel;
import residentevil.domain.models.view.VirusViewModel;

import java.util.List;

public interface VirusService {

    void spreadVirus(VirusServiceModel virusModel);

    List<VirusViewModel> extractAllViruses();

    VirusServiceModel getVirusById(String id);

    void editVirus(String id, VirusServiceModel virusServiceModel);

    void deleteVirusById(String id);

    boolean existsById(String id);

    String getAllVirusesWithAffectedCapitals();
}
