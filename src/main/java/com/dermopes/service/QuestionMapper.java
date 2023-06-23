package com.dermopes.service;

import com.dermopes.dto.QuestionCreateDto;
import com.dermopes.model.Answer;
import com.dermopes.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final AnswerMapper answerMapper;

    public Question toEntity(QuestionCreateDto request) {
        List<Answer> answers = request.getAnswers()
                .stream()
                .map(answerMapper::toEntity)
                .toList();
        return Question.builder()
                .content(request.getContent())
                .examDate(request.getExamDate())
                .categories(request.getCategories())
                .answers(answers)
                .build();
    }
}
