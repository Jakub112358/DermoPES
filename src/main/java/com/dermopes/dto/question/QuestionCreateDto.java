package com.dermopes.dto.question;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCreateDto {
    private String content;
    private LocalDate examDate;
    private Difficulty difficulty;
    private List<AnswerCreateDto> answers;
    private List<Category> categories;

}
