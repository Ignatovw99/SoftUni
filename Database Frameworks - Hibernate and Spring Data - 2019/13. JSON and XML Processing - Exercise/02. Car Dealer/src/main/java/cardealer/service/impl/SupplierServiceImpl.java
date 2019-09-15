package cardealer.service.impl;

import cardealer.domain.dto.binding.SupplierDto;
import cardealer.domain.dto.view.SupplierNonImporterViewDto;
import cardealer.domain.entity.Supplier;
import cardealer.repository.SupplierRepository;
import cardealer.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerSupplier(SupplierDto supplierDto) {
        Supplier supplier = this.modelMapper.map(supplierDto, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random = new Random();
        long suppliersCount = this.supplierRepository.count();
        long supplierId = random.nextInt((int) suppliersCount) + 1;
        return this.supplierRepository.findById(supplierId)
                .orElse(null);
    }

    @Transactional
    @Override
    public SupplierNonImporterViewDto[] getAllLocalSuppliers() {
        return this.modelMapper.map(this.supplierRepository.findAllByIsImporterFalse(), SupplierNonImporterViewDto[].class);
    }
}
