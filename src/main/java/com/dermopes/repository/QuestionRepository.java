package com.dermopes.repository;

import com.dermopes.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    @Query("""
    SELECT q FROM Question q
    LEFT JOIN FETCH q.answers
    """)
    List<Question> findAllAndFetchAnswers();

    @Query("""
    SELECT q FROM Question q
    LEFT JOIN FETCH q.answers
    WHERE q.id = :id
    """)
    Optional<Question> findByIdAndFetchAnswers(@Param("id") Long id);
}
