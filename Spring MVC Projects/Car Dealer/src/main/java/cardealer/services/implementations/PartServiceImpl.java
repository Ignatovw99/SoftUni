package cardealer.services.implementations;

import cardealer.domain.entities.Part;
import cardealer.domain.entities.Supplier;
import cardealer.domain.models.binding.PartAddEditBindingModel;
import cardealer.domain.models.service.parts.PartModifyServiceModel;
import cardealer.domain.models.service.parts.PartServiceModel;
import cardealer.repositories.CarRepository;
import cardealer.repositories.PartRepository;
import cardealer.repositories.SupplierRepository;
import cardealer.services.CarService;
import cardealer.services.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    private final CarRepository carRepository;

    private final SupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, CarRepository carRepository, SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    private void deleteCarsPartsContainsGivenPart(Part part) {
        this.carRepository.findAllContainingGivenPart(part)
                .forEach(car -> car.getParts().remove(part));
    }

    @Override
    public void addPart(PartModifyServiceModel partModifyServiceModel) {
        Part part = this.modelMapper.map(partModifyServiceModel, Part.class);
        part.setSupplier(this.supplierRepository.findByName(partModifyServiceModel.getSupplierName()));

        this.partRepository.saveAndFlush(part);
    }

    @Override
    public List<PartServiceModel> getAllParts() {
        return this.partRepository.findAll()
                .stream()
                .map(part -> this.modelMapper.map(part, PartServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PartServiceModel getPartById(Long id) {
        Part part = this.partRepository.findById(id).orElse(null);
        if (part == null) {
            return null;
        }
        return this.modelMapper.map(part, PartServiceModel.class);
    }

    @Override
    public void editPart(Long id, PartAddEditBindingModel partAddEditBindingModel) {
        Part part = this.modelMapper.map(partAddEditBindingModel, Part.class);
        Supplier supplier = this.supplierRepository.findByName(partAddEditBindingModel.getSupplierName());
        if (this.partRepository.findById(id).isEmpty() || supplier == null) {
            return;
        }
        part.setId(id);
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public void deletePart(Long id) {
        Part partToDelete = this.partRepository.findById(id).orElse(null);
        if (partToDelete == null) {
            return;
        }

        this.deleteCarsPartsContainsGivenPart(partToDelete);
        this.partRepository.delete(partToDelete);
    }
}
