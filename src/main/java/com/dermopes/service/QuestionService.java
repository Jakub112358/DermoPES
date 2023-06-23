package com.dermopes.service;

import com.dermopes.dto.QuestionCreateDto;
import com.dermopes.model.Question;
import com.dermopes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public Question save(QuestionCreateDto createDto){
        Question entity = questionMapper.toEntity(createDto);
        questionRepository.save(entity);
        return entity;
    }

}
