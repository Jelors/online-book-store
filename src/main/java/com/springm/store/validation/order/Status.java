package com.springm.store.validation.order;

import com.springm.store.validation.user.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {
    Class<? extends Enum<?>> enumClass();

    String message() default "Invalid order status!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
