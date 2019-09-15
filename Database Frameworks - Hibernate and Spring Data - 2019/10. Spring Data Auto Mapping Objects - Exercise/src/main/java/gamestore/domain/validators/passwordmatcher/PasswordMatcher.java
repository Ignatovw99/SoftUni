package gamestore.domain.validators.passwordmatcher;

import gamestore.constants.ValidationStatements;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordMatcherValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatcher {

    String message() default ValidationStatements.CONFIRM_PASSWORD_DOES_NOT_MATCH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
