package cardealer.common.validations.annotations;

import cardealer.common.validations.validators.SupplierValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = SupplierValidator.class)
public @interface IsSupplierPresent {

    String message() default "Invalid supplier";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
