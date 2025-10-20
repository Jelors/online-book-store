package com.springm.store.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String ISBN_13_PATTERN =
            "^(?:ISBN(?:-13)?:?\\s*)?(97[89][- ]?)\\d{1,5}[- ]?\\d{1,7}[- ]?\\d{1,7}[- ]?\\d$";

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(ISBN_13_PATTERN);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }
}
