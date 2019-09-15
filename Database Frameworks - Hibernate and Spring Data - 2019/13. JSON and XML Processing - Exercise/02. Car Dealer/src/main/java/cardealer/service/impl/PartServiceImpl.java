package cardealer.service.impl;

import cardealer.domain.dto.binding.PartDto;
import cardealer.domain.entity.Part;
import cardealer.repository.PartRepository;
import cardealer.service.PartService;
import cardealer.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class PartServiceImpl implements PartService {

    private PartRepository partRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPart(PartDto partDto) {
        Part part = this.modelMapper.map(partDto, Part.class);
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public void setSupplierToEachPart(SupplierService supplierService) {
        this.partRepository.findAll()
                .forEach(part -> {
                    part.setSupplier(supplierService.getRandomSupplier());
                    this.partRepository.saveAndFlush(part);
                });
    }

    @Override
    public Set<Part> getRandomParts(int partsCount) {
        Set<Part> randomParts = new HashSet<>();
        for (int i = 0; i < partsCount; i++) {
            randomParts.add(this.getRandomPart());
        }
        return randomParts;
    }

    private Part getRandomPart() {
        Random random = new Random();
        long partsCount = this.partRepository.count();
        long partId = random.nextInt((int) partsCount) + 1;
        return this.partRepository.findById(partId)
                .orElse(null);
    }
}
