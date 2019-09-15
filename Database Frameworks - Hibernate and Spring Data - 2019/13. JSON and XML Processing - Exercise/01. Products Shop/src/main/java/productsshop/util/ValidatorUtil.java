package productsshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class ValidatorUtil {

    private static Validator VALIDATOR;

    @Autowired
    private ValidatorUtil(Validator validator) {
        ValidatorUtil.VALIDATOR = validator;
    }

    public static <T> boolean isValid(T entity) {
        return ValidatorUtil.VALIDATOR.validate(entity).size() == 0;
    }

    public static <T> Set<String> getErrorConstraintMessages(T entity) {
        return ValidatorUtil.VALIDATOR.validate(entity)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }
}
