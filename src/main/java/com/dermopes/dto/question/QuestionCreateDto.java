package com.dermopes.dto.question;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.model.enumeration.Category;
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
    private List<AnswerCreateDto> answers;
    private List<Category> categories;
}