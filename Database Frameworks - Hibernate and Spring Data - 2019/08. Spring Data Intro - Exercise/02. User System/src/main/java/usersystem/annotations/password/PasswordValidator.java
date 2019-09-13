package usersystem.annotations.password;

import org.springframework.stereotype.Component;
import usersystem.annotations.AnnotationsProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static usersystem.constants.Constants.*;
import static usersystem.constants.PasswordPatterns.*;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private int minLength;

    private int maxLength;

    private boolean hasLowercase;

    private boolean hasUppercase;

    private boolean hasDigit;

    private boolean hasSpecialSymbol;

    @Override
    public void initialize(Password passwordAnnotation) {
        this.minLength = passwordAnnotation.minLength();
        this.maxLength = passwordAnnotation.maxLength();
        this.hasLowercase = passwordAnnotation.containsLowercase();
        this.hasUppercase = passwordAnnotation.containsUppercase();
        this.hasDigit = passwordAnnotation.containsDigit();
        this.hasSpecialSymbol = passwordAnnotation.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext constraintValidatorContext) {
        if (inputValue == null) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, PASSWORD_CAN_NOT_BE_EMPTY_FIELD);
            return false;
        }

        if (inputValue.length() < this.minLength) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, AGE_BELOW_LOWER_BOUND);
            return false;
        }

        if (inputValue.length() > this.maxLength) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, AGE_ABOVE_UPPER_BOUND);
            return false;
        }

        String passwordValue = inputValue.toString();

        if (!LOWERCASE_PATTERN.matcher(passwordValue).find() && this.hasLowercase) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, PASSWORD_SHOULD_CONTAIN_LOWERCASE);
            return false;
        }

        if (!UPPERCASE_PATTERN.matcher(passwordValue).find() && this.hasUppercase) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, PASSWORD_SHOULD_CONTAIN_UPPERCASE);
            return false;
        }

        if (!DIGIT_PATTERN.matcher(passwordValue).find() && this.hasDigit) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        if (!SPECIAL_SYMBOL_PATTERN.matcher(passwordValue).find() && this.hasSpecialSymbol) {
            AnnotationsProperties.setErrorMessage(constraintValidatorContext, PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL);
            return false;
        }

        return true;
    }
}
