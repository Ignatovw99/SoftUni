package gamestore.domain.validators.gametitle;

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
@Constraint(validatedBy = GameTitleValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTitle {

    String message() default ValidationStatements.GAME_TITLE_CAN_NOT_BE_EMPTY;

    int minLength() default ValidationConstraints.GAME_TITLE_MIN_LENGTH;

    int maxLength() default ValidationConstraints.GAME_TITLE_MAX_LENGTH;

    boolean firstLetterCapital() default ValidationConstraints.GAME_TITLE_FIRST_LETTER_IS_CAPITAL;

    boolean nullable() default ValidationConstraints.GAME_TITLE_CAN_BE_NULL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
