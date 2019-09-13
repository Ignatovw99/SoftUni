package usersystem.annotations.password;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static usersystem.constants.Constants.PASSWORD_CONSTRAINTS_NOT_FULFILLED;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    int minLength() default 6;

    int maxLength() default 30;

    boolean containsLowercase() default false;

    boolean containsUppercase() default false;

    boolean containsDigit() default false;

    boolean containsSpecialSymbol() default false;

    String message() default PASSWORD_CONSTRAINTS_NOT_FULFILLED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
