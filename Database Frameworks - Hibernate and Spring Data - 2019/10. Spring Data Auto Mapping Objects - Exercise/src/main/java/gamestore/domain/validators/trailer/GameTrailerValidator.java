package gamestore.domain.validators.trailer;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class GameTrailerValidator implements ConstraintValidator<GameTrailer, CharSequence> {
    private int length;

    private boolean nullable;

    private Pattern pattern;

    @Override
    public void initialize(GameTrailer gameTrailerAnnotation) {
        this.length = gameTrailerAnnotation.length();
        this.nullable = gameTrailerAnnotation.nullable();
        this.pattern = Pattern.compile(gameTrailerAnnotation.pattern());
    }

    @Override
    public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
        if (inputValue == null || inputValue.length() == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_TRAILER_CAN_NOT_BE_NULL);
                return false;
            }
        }

        if (inputValue.length() != this.length) {
            ValidatorUtils.setErrorMessage(validatorContext, String.format(ValidationStatements.GAME_TRAILER_DOES_NOT_HAVE_REQUIRED_LENGTH, this.length));
            return false;
        }

        if (!this.pattern.matcher(inputValue).matches()) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_TRAILER_DOES_NOT_MATCH_PATTERN);
            return false;
        }

        return true;
    }
}
