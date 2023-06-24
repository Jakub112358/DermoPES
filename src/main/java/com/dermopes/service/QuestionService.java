package com.dermopes.service;

import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionResponseDto save(QuestionCreateDto createDto) {
        Question entity = questionMapper.toEntity(createDto);
        questionRepository.save(entity);
        return questionMapper.toResponseDto(entity);
    }

    public QuestionResponseDto findById(Long id) {
        return questionMapper.toResponseDto(questionRepository.findByIdWithAnswers(id).orElseThrow(() -> new ResourceNotFoundException("Question", id)));
    }

    public List<QuestionResponseDto> findAll() {
        return questionRepository.findAllWithAnswers().stream()
                .map(questionMapper::toResponseDto)
                .toList();
    }

    public QuestionResponseDto update(Long id, QuestionUpdateDto updateDto) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", id));
        updateQuestion(question, updateDto);
        return questionMapper.toResponseDto(question);
    }

    public void deleteById(Long id) {
        throwIfDoesNotExist(id);
        questionRepository.deleteById(id);
    }

    private void throwIfDoesNotExist(Long id) {
        if (!questionRepository.existsById(id))
            throw new ResourceNotFoundException("Question", id);
    }

    private void updateQuestion(Question question, QuestionUpdateDto updateDto) {
        question.setContent(updateDto.getContent());
        question.setExamDate(updateDto.getExamDate());
        question.setCategories(updateDto.getCategories());
    }

}
