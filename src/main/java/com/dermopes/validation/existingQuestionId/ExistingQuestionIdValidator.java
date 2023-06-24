package com.dermopes.validation.existingQuestionId;

import com.dermopes.repository.QuestionRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingQuestionIdValidator implements ConstraintValidator<ExistingQuestionId, Long> {
    private final QuestionRepository questionRepository;

    @Override
    public boolean isValid(Long questionId, ConstraintValidatorContext context) {
        if (questionId == null)
            return false;
        return questionRepository.existsById(questionId);
    }
}
