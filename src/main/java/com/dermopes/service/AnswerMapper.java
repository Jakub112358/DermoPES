package com.dermopes.service;

import com.dermopes.dto.AnswerCreateDto;
import com.dermopes.dto.AnswerResponseDto;
import com.dermopes.model.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMapper {
    public Answer toEntity (AnswerCreateDto createDto){
        return Answer.builder()
                .content(createDto.getContent())
                .correct(createDto.isCorrect())
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
