package gamestore.domain.validators.gametitle;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GameTitleValidator implements ConstraintValidator<GameTitle, CharSequence> {

    private int minLength;

    private int maxLength;

    private boolean firstLetterCapital;

    private boolean nullable;

    @Override
    public void initialize(GameTitle gameTitleAnnotation) {
        this.minLength = gameTitleAnnotation.minLength();
        this.maxLength = gameTitleAnnotation.maxLength();
        this.firstLetterCapital = gameTitleAnnotation.firstLetterCapital();
        this.nullable = gameTitleAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
        if (inputValue == null || inputValue.length() == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_TITLE_CAN_NOT_BE_EMPTY);
                return false;
            }
        }

        String gameTitle = inputValue.toString().trim();

        if (gameTitle.length() < this.minLength || gameTitle.length() > this.maxLength) {
            ValidatorUtils.setErrorMessage(validatorContext,
                    String.format(ValidationStatements.GAME_TITLE_INVALID_LENGTH, this.minLength, this.maxLength));
            return false;
        }

        if (this.firstLetterCapital && !Character.isUpperCase(gameTitle.charAt(0))) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_TITLE_SHOULD_HAVE_CAPITAL_FIRST_LETTER);
            return false;
        }

        return true;
    }
}
