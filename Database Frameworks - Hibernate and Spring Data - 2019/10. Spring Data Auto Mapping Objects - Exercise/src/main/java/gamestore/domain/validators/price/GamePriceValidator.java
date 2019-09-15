package gamestore.domain.validators.price;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@Component
public class GamePriceValidator implements ConstraintValidator<GamePrice, BigDecimal> {

    private int precision;

    private boolean nullable;

    @Override
    public void initialize(GamePrice gamePriceAnnotation) {
        this.precision = gamePriceAnnotation.precision();
        this.nullable = gamePriceAnnotation.nullable();
    }

    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext validatorContext) {
        if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
            if (this.nullable) {
                return true;
            } else {
                ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_PRICE_CAN_NOT_BE_ZERO);
                return false;
            }
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_PRICE_CAN_NOT_BE_NEGATIVE);
            return false;
        }

        if (price.scale() > this.precision) {
            ValidatorUtils.setErrorMessage(validatorContext,
                    String.format(ValidationStatements.GAME_PRICE_SHOULD_BE_WITH_GIVEN_PRECISION, this.precision));
            return false;
        }

        return true;
    }
}
