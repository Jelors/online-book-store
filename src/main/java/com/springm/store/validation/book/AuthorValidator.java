package com.springm.store.validation.book;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorValidator implements ConstraintValidator<Author, String> {
    private static final int MINIMUM_NAME_LENGTH = 10;

    @Override
    public boolean isValid(String authorName,
                           ConstraintValidatorContext constraintValidatorContext) {
        return authorName != null && authorName.length() >= MINIMUM_NAME_LENGTH;
    }
}
