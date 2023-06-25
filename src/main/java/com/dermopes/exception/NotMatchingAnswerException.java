package com.dermopes.exception;

public class NotMatchingAnswerException extends RuntimeException{
    public NotMatchingAnswerException(Long questionId, Long answerId) {
        super("Answer with id: " + answerId + " doesn't refer to question with id: " + questionId);
    }
}
