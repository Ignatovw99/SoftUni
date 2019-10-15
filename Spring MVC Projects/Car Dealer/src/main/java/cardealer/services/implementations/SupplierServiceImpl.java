package cardealer.services.implementations;

import cardealer.domain.entities.Supplier;
import cardealer.domain.models.service.suppliers.SupplierWithPartsDetailsServiceModel;
import cardealer.repositories.PartRepository;
import cardealer.repositories.SupplierRepository;
import cardealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final PartRepository partRepository;

    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    private Integer getCountOfOfferedParts(Supplier supplier) {
        return this.partRepository.findAllBySupplier(supplier).size();
    }

    private SupplierWithPartsDetailsServiceModel initSupplierServiceModel(Supplier supplier) {
        SupplierWithPartsDetailsServiceModel supplierServiceModel = this.modelMapper.map(supplier, SupplierWithPartsDetailsServiceModel.class);
        supplierServiceModel.setNumberOfOfferedParts(this.getCountOfOfferedParts(supplier));
        return supplierServiceModel;
    }

    @Override
    public List<SupplierWithPartsDetailsServiceModel> getAllLocalSuppliers() {
        return this.supplierRepository.findAllByIsImporterFalse()
                .stream()
                .map(this::initSupplierServiceModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierWithPartsDetailsServiceModel> getAllImporterSuppliers() {
        return this.supplierRepository.findAllByIsImporterTrue()
                .stream()
                .map(this::initSupplierServiceModel)
                .collect(Collectors.toList());
    }
}
