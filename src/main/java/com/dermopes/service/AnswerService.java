package com.dermopes.service;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.model.Answer;
import com.dermopes.repository.AnswerRepository;
import com.dermopes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerResponseDto save(Long questionId, AnswerCreateDto createDto) {
        throwIfQuestionDoesNotExist(questionId);

        Answer answer = answerMapper.toEntity(createDto, questionId);
        answerRepository.save(answer);
        return answerMapper.toResponseDto(answer);
    }

    public List<AnswerResponseDto> findAll(Long questionId) {
        throwIfQuestionDoesNotExist(questionId);

        return answerRepository.findAllByQuestionId(questionId).stream()
                .map(answerMapper::toResponseDto)
                .toList();
    }

    public AnswerResponseDto findById(Long questionId, Long answerId) {
        throwIfQuestionDoesNotExist(questionId);
        return answerMapper.toResponseDto(answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer", answerId)));
    }

    public AnswerResponseDto update(Long questionId, Long answerId, AnswerCreateDto createDto) {
        throwIfQuestionDoesNotExist(questionId);

        throwIfAnswerDoesNotExist(answerId);
        answerRepository.deleteById(answerId);

        Answer newAnswer = answerMapper.toEntity(createDto, questionId);
        answerRepository.save(newAnswer);
        return answerMapper.toResponseDto(newAnswer);
    }

    public void deleteById(Long questionId, Long answerId) {
        throwIfQuestionDoesNotExist(questionId);
        throwIfAnswerDoesNotExist(answerId);
        answerRepository.deleteById(answerId);
    }

    private void throwIfQuestionDoesNotExist(Long id) {
        if (!questionRepository.existsById(id))
            throw new ResourceNotFoundException("Question", id);
    }

    private void throwIfAnswerDoesNotExist(Long id) {
        if (!answerRepository.existsById(id))
            throw new ResourceNotFoundException("Answer", id);
    }
}
