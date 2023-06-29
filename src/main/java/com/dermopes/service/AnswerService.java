package com.dermopes.service;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.exception.NotMatchingAnswerException;
import com.dermopes.exception.ResourceNotFoundException;
import com.dermopes.model.Answer;
import com.dermopes.repository.AnswerRepository;
import com.dermopes.service.util.AnswerMapper;
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

    public AnswerResponseDto save(Long questionId, AnswerCreateDto createDto) {
        Answer answer = answerMapper.toEntity(createDto, questionId);
        answerRepository.save(answer);
        return answerMapper.toResponseDto(answer);
    }

    public List<AnswerResponseDto> findByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId).stream()
                .map(answerMapper::toResponseDto)
                .toList();
    }

    public AnswerResponseDto findById(Long answerId) {
        return answerMapper.toResponseDto(answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer", answerId)));
    }


    public AnswerResponseDto update(Long questionId, Long answerId, AnswerCreateDto createDto) {
        throwIfAnswerDoesntReferToQuestion(questionId, answerId);

        answerRepository.deleteById(answerId);
        Answer newAnswer = answerMapper.toEntity(createDto, questionId);
        answerRepository.save(newAnswer);
        return answerMapper.toResponseDto(newAnswer);
    }

    public void deleteById(Long questionId, Long answerId) {
        throwIfAnswerDoesntReferToQuestion(questionId, answerId);

        answerRepository.deleteById(answerId);
    }

    private void throwIfAnswerDoesntReferToQuestion(Long questionId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer", answerId));
        if (!questionId.equals(answer.getQuestion().getId())) {
            throw new NotMatchingAnswerException(questionId, answerId);
        }
    }

}
