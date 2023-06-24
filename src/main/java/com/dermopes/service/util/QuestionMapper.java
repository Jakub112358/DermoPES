package com.dermopes.service.util;

import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.model.Answer;
import com.dermopes.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final AnswerMapper answerMapper;

    public Question toEntity(QuestionCreateDto request) {

        Question question = new Question();
        List<Answer> answers;
        if (request.getAnswers() != null) {
            answers = request.getAnswers().stream()
                    .map(answerMapper::toEntity)
                    .peek(a -> a.setQuestion(question))
                    .toList();
        } else {
            answers = new ArrayList<>();
        }
        question.setContent(request.getContent());
        question.setExamDate(request.getExamDate());
        question.setDifficulty(request.getDifficulty());
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
                .difficulty(entity.getDifficulty())
                .categories(entity.getCategories())
                .answers(answers)
                .build();
    }
}
