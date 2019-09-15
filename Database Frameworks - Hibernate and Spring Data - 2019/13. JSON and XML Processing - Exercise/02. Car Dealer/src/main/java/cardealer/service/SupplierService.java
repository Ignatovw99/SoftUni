package cardealer.service;

import cardealer.domain.dto.binding.SupplierDto;
import cardealer.domain.dto.view.SupplierNonImporterViewDto;
import cardealer.domain.entity.Supplier;

public interface SupplierService {

    void registerSupplier(SupplierDto supplierDto);

    Supplier getRandomSupplier();

    SupplierNonImporterViewDto[] getAllLocalSuppliers();
}
