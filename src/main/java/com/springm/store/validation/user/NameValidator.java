package com.springm.store.validation.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
    private static final int MIN_NAME_LENGTH = 4;

    @Override
    public boolean isValid(String name,
                           ConstraintValidatorContext constraintValidatorContext) {
        return name != null && name.length() >= MIN_NAME_LENGTH;
    }
}
