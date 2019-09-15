package gamestore.domain.validators.email;

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
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default ValidationStatements.EMAIL_CAN_NOT_BE_EMPTY;

    boolean nullable() default ValidationConstraints.EMAIL_ADDRESS_CAN_BE_NULL;

    String pattern() default ValidationConstraints.EMAIL_VALIDATION_PATTERN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
