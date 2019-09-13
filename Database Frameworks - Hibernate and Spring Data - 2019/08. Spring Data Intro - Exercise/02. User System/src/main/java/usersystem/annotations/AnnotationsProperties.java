package usersystem.annotations;

import javax.validation.ConstraintValidatorContext;

public class AnnotationsProperties {

    public static void setErrorMessage(ConstraintValidatorContext validatorContext, String errorMessage) {
        validatorContext.disableDefaultConstraintViolation();
        validatorContext.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
