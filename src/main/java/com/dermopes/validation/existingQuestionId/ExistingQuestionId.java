package com.dermopes.validation.existingQuestionId;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingQuestionIdValidator.class)
@Documented
public @interface ExistingQuestionId {
    String message() default "Question with given id doesn't exist in the database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
