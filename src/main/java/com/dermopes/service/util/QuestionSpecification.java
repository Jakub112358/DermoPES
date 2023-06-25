package com.dermopes.service.util;

import com.dermopes.dto.question.QuestionCriteriaDto;
import com.dermopes.model.Question;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class QuestionSpecification implements Specification<Question> {
    private final QuestionCriteriaDto criteriaDto;

    @Override
    public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                dateIsGreaterThanOrEqualTo(root, criteriaBuilder),
                dateIsLesserThanOrEqualTo(root, criteriaBuilder),
                categoryIsMember(root,criteriaBuilder),
                difficultyIsMember(root,criteriaBuilder)
        );
    }

    private Predicate difficultyIsMember(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getDifficultyOf()) ?
                criteriaBuilder.or(getDifficultyPredicatesArray(root, criteriaBuilder)) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate[] getDifficultyPredicatesArray(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return criteriaDto.getDifficultyOf().stream()
                .map(d -> criteriaBuilder.equal(root.get("difficulty"), d))
                .toArray(Predicate[]::new);
    }

    private Predicate categoryIsMember(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getCategoryOf()) ?
                criteriaBuilder.or(getCategoryPredicatesArray(root, criteriaBuilder)) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate[] getCategoryPredicatesArray(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return criteriaDto.getCategoryOf().stream()
                .map(category -> criteriaBuilder.isMember(category, root.get("categories")))
                .toArray(Predicate[]::new);
    }

    private Predicate dateIsLesserThanOrEqualTo(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getExamDateTo()) ?
                criteriaBuilder.lessThanOrEqualTo(root.get("examDate"), criteriaDto.getExamDateTo()) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate dateIsGreaterThanOrEqualTo(Root<Question> root, CriteriaBuilder criteriaBuilder) {
        return nonNull(criteriaDto.getExamDateFrom()) ?
                criteriaBuilder.greaterThanOrEqualTo(root.get("examDate"), criteriaDto.getExamDateFrom()) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
