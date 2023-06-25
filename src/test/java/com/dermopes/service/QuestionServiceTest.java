package com.dermopes.service;

import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import com.dermopes.service.util.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @InjectMocks
    private QuestionService questionService;
    @Mock
    private QuestionMapper questionMapper;
    @Mock
    QuestionRepository questionRepository;

    @Test
    public void whenSave_thenShouldReturnResponseDto(){
        //given
        QuestionCreateDto createDto = new QuestionCreateDto();
        Question entity = new Question();
        QuestionResponseDto responseDto = new QuestionResponseDto();

        //and
        when(questionMapper.toEntity(createDto)).thenReturn(entity);
        when(questionRepository.save(entity)).thenReturn(entity);
        when(questionMapper.toResponseDto(entity)).thenReturn(responseDto);

        //when
        QuestionResponseDto result = questionService.save(createDto);

        //then
        verify(questionMapper).toEntity(createDto);
        verify(questionRepository).save(entity);
        verify(questionMapper).toResponseDto(entity);
        assertEquals(responseDto, result);
    }

    @Test
    public void whenUpdate_thenShouldReturnResponse(){
        //given
        Long id = 1L;
        QuestionUpdateDto updateDto = new QuestionUpdateDto();
        Question question = new Question();
        QuestionResponseDto responseDto = new QuestionResponseDto();

        //and
        when(questionRepository.findById(id)).thenReturn(Optional.of(question));
        when(questionMapper.toResponseDto(question)).thenReturn(responseDto);

        //when
        QuestionResponseDto result = questionService.update(id, updateDto);

        //then
        verify(questionRepository).findById(id);
        verify(questionMapper).toResponseDto(question);
        assertEquals(responseDto, result);
    }

    @Test
    public void whenUpdate_thenNotExistingQuestion(){
        //given
        Long id = 1L;
        QuestionUpdateDto updateDto = new QuestionUpdateDto();

        //and
        when(questionRepository.findById(id)).thenReturn(Optional.empty());

        //when and then
        assertThrows(ResourceNotFoundException.class, () -> questionService.update(id, updateDto));

        //and
        verify(questionRepository).findById(id);
        verify(questionMapper, never()).toResponseDto(any());
    }

}
