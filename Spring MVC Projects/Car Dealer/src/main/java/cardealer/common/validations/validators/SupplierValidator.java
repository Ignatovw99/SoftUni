package cardealer.common.validations.validators;

import cardealer.common.validations.annotations.IsSupplierPresent;
import cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SupplierValidator implements ConstraintValidator<IsSupplierPresent, String> {

   private SupplierRepository supplierRepository;

   @Autowired
   public SupplierValidator(SupplierRepository supplierRepository) {
      this.supplierRepository = supplierRepository;
   }

   public void initialize(IsSupplierPresent constraint) {
   }

   public boolean isValid(String supplierName, ConstraintValidatorContext context) {
      return this.supplierRepository.existsByName(supplierName);
   }
}
