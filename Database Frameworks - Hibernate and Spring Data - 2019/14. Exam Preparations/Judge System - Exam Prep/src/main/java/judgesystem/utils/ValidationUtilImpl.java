package judgesystem.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> Boolean isValid(T entity) {
        return this.validator.validate(entity).size() == 0;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> constraintViolations(T entity) {
        return this.validator.validate(entity);
    }
}
