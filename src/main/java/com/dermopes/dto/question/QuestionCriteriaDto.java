package com.dermopes.dto.question;

import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCriteriaDto {
    private LocalDate examDateFrom;
    private LocalDate examDateTo;
    private Set<Category> categoryOf;
    private Set<Difficulty> difficultyOf;

}
