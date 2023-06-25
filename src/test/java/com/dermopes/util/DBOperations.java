package com.dermopes.util;


import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DBOperations {
    private final QuestionRepository questionRepository;

    public void cleanQuestionTable() {
        questionRepository.deleteAll();
    }


    public Question addSimpleQuestionToDb() {
        var question = QuestionFactory.getSimpleQuestionBuilder().build();
        return questionRepository.save(question);
    }
}
