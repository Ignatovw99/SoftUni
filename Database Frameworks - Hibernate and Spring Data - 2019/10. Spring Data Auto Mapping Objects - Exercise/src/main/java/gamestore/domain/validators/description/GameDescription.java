package gamestore.domain.validators.description;

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
@Constraint(validatedBy = GameDescriptionValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameDescription {

    String message() default ValidationStatements.GAME_DESCRIPTION_CAN_NOT_BE_NULL;

    int minLength() default ValidationConstraints.GAME_DESCRIPTION_DEFAULT_MIN_LENGTH;

    boolean nullable() default ValidationConstraints.GAME_DESCRIPTION_CAN_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
