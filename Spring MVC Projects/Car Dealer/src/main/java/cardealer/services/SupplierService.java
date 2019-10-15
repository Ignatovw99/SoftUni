package cardealer.services;

import cardealer.domain.models.service.suppliers.SupplierWithPartsDetailsServiceModel;

import java.util.List;

public interface SupplierService {

    List<SupplierWithPartsDetailsServiceModel> getAllLocalSuppliers();

    List<SupplierWithPartsDetailsServiceModel> getAllImporterSuppliers();
}
