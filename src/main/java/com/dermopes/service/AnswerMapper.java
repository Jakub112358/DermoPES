package com.dermopes.service;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.model.Answer;
import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMapper {
    private final QuestionRepository questionRepository;
    public Answer toEntity (AnswerCreateDto createDto){
        return Answer.builder()
                .content(createDto.getContent())
                .correct(createDto.isCorrect())
                .build();
    }

    public Answer toEntity (AnswerCreateDto createDto, Long questionId){
        Question question = questionRepository.getReferenceById(questionId);

        return Answer.builder()
                .content(createDto.getContent())
                .correct(createDto.isCorrect())
                .question(question)
                .build();
    }

    public AnswerResponseDto toResponseDto(Answer answer) {
        return AnswerResponseDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .correct(answer.isCorrect())
                .build();
    }
}
