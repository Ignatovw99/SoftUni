package gamestore.domain.validators.password;

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
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default ValidationStatements.PASSWORD_CAN_NOT_BE_EMPTY;

    int minLength() default ValidationConstraints.PASSWORD_MIN_LENGTH;

    boolean containsUppercase() default ValidationConstraints.PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_UPPERCASE;

    boolean containsLowercase() default ValidationConstraints.PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_LOWERCASE;

    boolean containsDigit() default ValidationConstraints.PASSWORD_SHOULD_CONTAIN_AT_LEAST_ONE_DIGIT;

    boolean nullable() default ValidationConstraints.PASSWORD_IS_MANDATORY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
