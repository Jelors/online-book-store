package com.springm.store.validation.category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Category, String> {
    private static final int MINIMUM_NAME_LENGTH = 4;

    @Override
    public boolean isValid(
            String categoryName,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return categoryName != null && categoryName.length() >= MINIMUM_NAME_LENGTH;
    }
}
