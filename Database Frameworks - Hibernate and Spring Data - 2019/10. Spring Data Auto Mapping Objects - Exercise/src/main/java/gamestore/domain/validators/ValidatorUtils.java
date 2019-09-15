package gamestore.domain.validators;

import javax.validation.ConstraintValidatorContext;

public class ValidatorUtils {

    private ValidatorUtils(){
    }

    public static void setErrorMessage(ConstraintValidatorContext validatorContext, String errorMessage) {
        validatorContext.disableDefaultConstraintViolation();
        validatorContext.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
