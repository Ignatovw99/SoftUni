package gamestore.domain.validators.size;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@Component
public class GameSizeValidator implements ConstraintValidator<GameSize, Double> {

    private int precision;

    private boolean nullable;

    @Override
    public void initialize(GameSize gameSizeAnnotation) {
        this.precision = gameSizeAnnotation.precision();
        this.nullable = gameSizeAnnotation.nullable();
    }

    @Override
    public boolean isValid(Double size, ConstraintValidatorContext validatorContext) {
        if (size == null || size == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_SIZE_CAN_NOT_BE_EMPTY);
                return false;
            }
        }

        if (size < 0) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_SIZE_CAN_NOT_BE_NEGATIVE);
            return false;
        }

        if (new BigDecimal(size).scale() > this.precision) {
            ValidatorUtils.setErrorMessage(validatorContext,
                    String.format(ValidationStatements.GAME_SIZE_SHOULD_BE_WITH_GIVEN_PRECISION, this.precision));
            return false;
        }

        return true;
    }
}
