package gamestore.domain.validators.thumbnailurl;

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
@Constraint(validatedBy = ThumbnailURLValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThumbnailURL {

    String message() default ValidationStatements.GAME_THUMBNAIL_URL_CAN_NOT_BE_NULL;

    String pattern() default ValidationConstraints.GAME_THUMBNAIL_URL_VALIDATION_PATTERN;

    boolean nullable() default ValidationConstraints.GAME_THUMBNAIL_URL_CAN_BE_EMPTY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
