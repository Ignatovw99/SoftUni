package gamestore.domain.validators.price;

import gamestore.constants.ValidationConstraints;
import gamestore.constants.ValidationStatements;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = GamePriceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GamePrice {

    String message() default ValidationStatements.GAME_PRICE_CAN_NOT_BE_NEGATIVE;

    int precision() default ValidationConstraints.GAME_PRICE_DEFAULT_PRECISION_AFTER_DECIMAL_POINT;

    boolean nullable() default ValidationConstraints.GAME_PRICE_CAN_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
