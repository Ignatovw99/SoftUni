package gamestore.domain.validators.password;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");

    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");


    private int minLength;

    private boolean hasUppercase;

    private boolean hasLowercase;

    private boolean hasDigit;

    private boolean nullable;

    @Override
    public void initialize(Password passwordAnnotation) {
        this.minLength = passwordAnnotation.minLength();
        this.hasUppercase = passwordAnnotation.containsUppercase();
        this.hasLowercase = passwordAnnotation.containsLowercase();
        this.hasDigit = passwordAnnotation.containsDigit();
        this.nullable = passwordAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
        if (inputValue == null || inputValue.length() == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.PASSWORD_CAN_NOT_BE_EMPTY);
                return false;
            }
        }

        String password = inputValue.toString();

        if (password.length() < this.minLength) {
            ValidatorUtils.setErrorMessage(validatorContext,
                    String.format(ValidationStatements.INVALID_PASSWORD_LENGTH, this.minLength));
            return false;
        }

        if (this.hasUppercase && !PasswordValidator.UPPERCASE_PATTERN.matcher(password).find()) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.PASSWORD_SHOULD_CONTAIN_UPPERCASE);
            return false;
        }

        if (this.hasLowercase && !PasswordValidator.LOWERCASE_PATTERN.matcher(password).find()) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.PASSWORD_SHOULD_CONTAIN_LOWERCASE);
            return false;
        }

        if (this.hasDigit && !PasswordValidator.DIGIT_PATTERN.matcher(password).find()) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        return true;
    }
}
