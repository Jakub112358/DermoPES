package com.dermopes.service;

import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
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

        Question question = new Question();
        List<Answer> answers = request.getAnswers().stream()
                .map(answerMapper::toEntity)
                .peek(a -> a.setQuestion(question))
                .toList();

        question.setContent(request.getContent());
        question.setExamDate(request.getExamDate());
        question.setCategories(request.getCategories());
        question.setAnswers(answers);

        return question;

    }

    public QuestionResponseDto toResponseDto(Question entity) {
        List<AnswerResponseDto> answers = entity.getAnswers().stream()
                .map(answerMapper::toResponseDto)
                .toList();

        return QuestionResponseDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .examDate(entity.getExamDate())
                .categories(entity.getCategories())
                .answers(answers)
                .build();
    }
}
