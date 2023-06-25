package com.dermopes.validation.existingAnswerId;

import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.repository.AnswerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingAnswerIdValidator implements ConstraintValidator<ExistingAnswerId, Long> {
    private final AnswerRepository answerRepository;

    @Override
    public boolean isValid(Long answerId, ConstraintValidatorContext context) {
        if (answerId == null)
            return false;
        if(!answerRepository.existsById(answerId))
            throw new ResourceNotFoundException("Answer", answerId);
        return true;
    }
}
