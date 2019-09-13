package usersystem.annotations.email;

import org.springframework.stereotype.Component;
import usersystem.annotations.AnnotationsProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static usersystem.constants.Constants.EMAIL_CAN_NOT_BE_EMPTY_FIELD;
import static usersystem.constants.Constants.EMAIL_CONSTRAINTS_NOT_FULFILLED;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private Pattern emailPattern;

    @Override
    public void initialize(Email emailAnnotation) {
        this.emailPattern = Pattern.compile(emailAnnotation.emailPattern());
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext constraintValidatorContext) {
        if (inputValue == null) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, EMAIL_CAN_NOT_BE_EMPTY_FIELD);
            return false;
        }

        if (!this.emailPattern.matcher(inputValue).find()) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, EMAIL_CONSTRAINTS_NOT_FULFILLED);
            return false;
        }

        return true;
    }
}
