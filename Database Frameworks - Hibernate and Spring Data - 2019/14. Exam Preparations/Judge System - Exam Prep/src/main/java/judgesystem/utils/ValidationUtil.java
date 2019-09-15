package judgesystem.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <T> Boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> constraintViolations(T entity);
}
