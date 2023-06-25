package com.dermopes.service.util;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.model.Answer;
import com.dermopes.model.Question;
import com.dermopes.util.QuestionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionMapperTest {
    @InjectMocks
    private QuestionMapper questionMapper;
    @Mock
    private AnswerMapper answerMapper;

    @Test
    public void whenToEntity_WithAnswers_thenShouldReturnCorrect() {
        //given
        QuestionCreateDto request = QuestionFactory.getSimpleQuestionCreateDtoBuilder().build();
        AnswerCreateDto answerDto1 = new AnswerCreateDto("Answer 1", true);
        AnswerCreateDto answerDto2 = new AnswerCreateDto("Answer 2", false);
        request.setAnswers(List.of(answerDto1, answerDto2));

        //and
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();

        when(answerMapper.toEntity(answerDto1)).thenReturn(answer1);
        when(answerMapper.toEntity(answerDto2)).thenReturn(answer2);

        // when
        Question result = questionMapper.toEntity(request);

        // then
        verify(answerMapper).toEntity(answerDto1);
        verify(answerMapper).toEntity(answerDto2);

        //and
        assertEquals(QuestionFactory.simpleContent, result.getContent());
        assertEquals(QuestionFactory.simpleExamDate, result.getExamDate());
        assertEquals(QuestionFactory.simpleDifficulty, result.getDifficulty());
        assertEquals(QuestionFactory.simpleCategories, result.getCategories());
        assertEquals(2, result.getAnswers().size());
        assertEquals(answer1, result.getAnswers().get(0));
        assertEquals(answer2, result.getAnswers().get(1));
    }

    @Test
    public void whenToEntity_WithoutAnswers_thenShouldReturnCorrect() {
        //given
        QuestionCreateDto request = QuestionFactory.getSimpleQuestionCreateDtoBuilder().build();
        request.setAnswers(null);

        //when
        Question result = questionMapper.toEntity(request);

        //then
        assertEquals(QuestionFactory.simpleContent, result.getContent());
        assertEquals(QuestionFactory.simpleExamDate, result.getExamDate());
        assertEquals(QuestionFactory.simpleDifficulty, result.getDifficulty());
        assertEquals(QuestionFactory.simpleCategories, result.getCategories());
        assertEquals(new ArrayList<>(), result.getAnswers());
    }

    @Test
    public void whenToResponseDto_thenShouldReturnCorrect() {
        //given
        Question question = QuestionFactory.getSimpleQuestionBuilder().build();
        question.setId(1L);

        //and
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        question.setAnswers(List.of(answer1, answer2));

        //and
        AnswerResponseDto answerDto1 = new AnswerResponseDto();
        AnswerResponseDto answerDto2 = new AnswerResponseDto();

        when(answerMapper.toResponseDto(answer1)).thenReturn(answerDto1);
        when(answerMapper.toResponseDto(answer2)).thenReturn(answerDto2);

        //when
        QuestionResponseDto result = questionMapper.toResponseDto(question);

        //then
        verify(answerMapper).toResponseDto(answer1);
        verify(answerMapper).toResponseDto(answer2);

        //and
        assertEquals(1L, result.getId());
        assertEquals(QuestionFactory.simpleContent, result.getContent());
        assertEquals(QuestionFactory.simpleExamDate, result.getExamDate());
        assertEquals(QuestionFactory.simpleDifficulty, result.getDifficulty());
        assertEquals(QuestionFactory.simpleCategories, result.getCategories());
        assertEquals(List.of(answerDto1, answerDto2), result.getAnswers());

    }

}
