package me.seungwoo;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wisebirds on 2017-09-13.
 */
public class ValidatorUtils {
    public static <T> ConstraintViolation<T> constraintViolation(T obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (violations.size() > 0) {
            for (ConstraintViolation<T> v : violations) {
                return v;
            }
        }
        return null;
    }

    /**
     * String의 null 또는 white space 값을 제거하고 반환.
     *
     * @param oldValue List<String>
     * @return List<String>
     */
    public static List<String> getStringListValueExceptSpaceOrNull(List<String> oldValue) {
        List<String> newValue = oldValue.stream()
                .filter(t -> t != null && t.trim().length() > 0)
                .map(t -> t.trim())
                .collect(Collectors.toList());

        return newValue;
    }
}
