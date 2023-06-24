package com.dermopes.validation.isAfter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsAfterValidator.class)
@Documented
public @interface IsAfter {
    String message() default "Given date is not in valid range";
    String current();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
