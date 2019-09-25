package residentevil.common.validators;

import residentevil.common.annotations.IsDateBeforeToday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IsDateBeforeTodayValidator implements ConstraintValidator<IsDateBeforeToday, LocalDate> {

   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
      if (date == null) {
         return false;
      }
      return date.isBefore(LocalDate.now());
   }
}
