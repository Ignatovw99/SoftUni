package cardealer.service;

import cardealer.domain.dto.binding.PartDto;
import cardealer.domain.entity.Part;

import java.util.Set;

public interface PartService {

    void addPart(PartDto partDto);

    void setSupplierToEachPart(SupplierService supplierService);

    Set<Part> getRandomParts(int partsCount);
}
