package gamestore.domain.validators.description;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GameDescriptionValidator implements ConstraintValidator<GameDescription, CharSequence> {

    private boolean nullable;

    private int minLength;


    public void initialize(GameDescription gameDescriptionAnnotation) {
        this.nullable = gameDescriptionAnnotation.nullable();
        this.minLength = gameDescriptionAnnotation.minLength();
    }

    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
        if (inputValue == null || inputValue.length() == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_DESCRIPTION_CAN_NOT_BE_NULL);
                return false;
            }
        }

        if (inputValue.length() < this.minLength) {
            ValidatorUtils.setErrorMessage(validatorContext, String.format(ValidationStatements.GAME_DESCRIPTION_IS_INVALID, this.minLength));
            return false;
        }

        return true;
    }
}
