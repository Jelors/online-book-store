package com.springm.store.validation.book;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<Title, String> {
    private static final int MINIMUM_TITLE_LENGTH = 4;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        return title != null && title.length() >= MINIMUM_TITLE_LENGTH;
    }
}
