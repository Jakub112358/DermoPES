package com.dermopes.repository;

import com.dermopes.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    void deleteByQuestionId(Long questionId);
}
