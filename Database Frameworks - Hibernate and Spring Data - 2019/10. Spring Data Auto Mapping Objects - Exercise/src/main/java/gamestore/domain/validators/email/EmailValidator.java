package gamestore.domain.validators.email;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private boolean nullable;

    private Pattern pattern;

    @Override
    public void initialize(Email emailAnnotation) {
        this.nullable = emailAnnotation.nullable();
        this.pattern = Pattern.compile(emailAnnotation.pattern());
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
        if (inputValue == null) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.EMAIL_CAN_NOT_BE_NULL);
                return false;
            }
        }

        String email = inputValue.toString();

        if (!this.pattern.matcher(email).matches()) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.EMAIL_INVALID_VALUE);
            return false;
        }

        return true;
    }
}
