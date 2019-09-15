package gamestore.domain.validators.trailer;

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
@Constraint(validatedBy = GameTrailerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTrailer {

    String message() default ValidationStatements.GAME_TRAILER_CAN_NOT_BE_NULL;

    int length() default ValidationConstraints.GAME_TRAILER_DEFAULT_LENGTH;

    String pattern() default ValidationConstraints.GAME_TRAILER_DEFAULT_PATTERN;

    boolean nullable() default ValidationConstraints.GAME_TRAILER_CAN_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
