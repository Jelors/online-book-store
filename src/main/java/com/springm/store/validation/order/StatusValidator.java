package com.springm.store.validation.order;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class StatusValidator implements ConstraintValidator<Status, String> {
    private Set<String> values;

    @Override
    public void initialize(Status constraintAnnotation) {
        values = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String status,
                           ConstraintValidatorContext constraintValidatorContext
    ) {
        return status != null && values.contains(status);
    }

}
