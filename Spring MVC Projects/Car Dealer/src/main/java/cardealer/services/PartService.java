package cardealer.services;

import cardealer.domain.models.binding.PartAddEditBindingModel;
import cardealer.domain.models.service.parts.PartModifyServiceModel;
import cardealer.domain.models.service.parts.PartServiceModel;

import java.util.List;

public interface PartService {

    void addPart(PartModifyServiceModel partModifyServiceModel);

    List<PartServiceModel> getAllParts();

    void deletePart(Long id);

    PartServiceModel getPartById(Long id);

    void editPart(Long id, PartAddEditBindingModel partAddEditBindingModel);
}
