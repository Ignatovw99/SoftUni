package gamestore.domain.validators.size;

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
@Constraint(validatedBy = GameSizeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameSize {

    String message() default ValidationStatements.GAME_SIZE_CAN_NOT_BE_EMPTY;

    int precision() default ValidationConstraints.GAME_SIZE_DEFAULT_PRECISION_AFTER_DECIMAL_POINT;

    boolean nullable() default ValidationConstraints.GAME_SIZE_CAN_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
