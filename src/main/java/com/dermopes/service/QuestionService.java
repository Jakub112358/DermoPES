package com.dermopes.service;

import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionCriteriaDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import com.dermopes.service.util.QuestionMapper;
import com.dermopes.service.util.QuestionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
        return questionMapper.toResponseDto(questionRepository.findByIdAndFetchAnswers(id).orElseThrow(() -> new ResourceNotFoundException("Question", id)));
    }

    public List<QuestionResponseDto> findAll() {
        return questionRepository.findAllAndFetchAnswers().stream()
                .map(questionMapper::toResponseDto)
                .toList();
    }

    public QuestionResponseDto update(Long id, QuestionUpdateDto updateDto) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question", id));
        updateQuestion(question, updateDto);
        return questionMapper.toResponseDto(question);
    }

    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<QuestionResponseDto> findByCriteria(QuestionCriteriaDto criteria) {
        Specification<Question> specification = new QuestionSpecification(criteria);
        return questionRepository.findAll(specification).stream()
                .map(questionMapper::toResponseDto)
                .toList();
    }

    private void updateQuestion(Question question, QuestionUpdateDto updateDto) {
        question.setContent(updateDto.getContent());
        question.setExamDate(updateDto.getExamDate());
        question.setCategories(updateDto.getCategories());
        question.setDifficulty(updateDto.getDifficulty());
    }


}
