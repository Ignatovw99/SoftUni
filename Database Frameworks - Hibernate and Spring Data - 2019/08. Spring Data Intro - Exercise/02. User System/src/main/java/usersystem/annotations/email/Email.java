package usersystem.annotations.email;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static usersystem.constants.Constants.EMAIL_FORMAT_NOT_VALID;

@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String emailPattern() default "^([A-Za-z0-9][.\\-_]?)+([A-Za-z0-9])@([a-zA-Z][-]?)+([a-zA-Z])(\\.([a-zA-Z][-]?)+([a-zA-z]))+$";

    String message() default EMAIL_FORMAT_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
