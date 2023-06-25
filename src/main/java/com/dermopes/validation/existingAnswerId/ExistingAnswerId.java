package com.dermopes.validation.existingAnswerId;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingAnswerIdValidator.class)
@Documented
public @interface ExistingAnswerId {
    String message() default "Answer with given id doesn't exist in the database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
