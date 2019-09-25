package residentevil.common.annotations;

import residentevil.common.validators.IsDateBeforeTodayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = IsDateBeforeTodayValidator.class)
public @interface IsDateBeforeToday {

    String message() default "Invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
