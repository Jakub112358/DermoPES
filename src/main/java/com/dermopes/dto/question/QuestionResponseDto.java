package com.dermopes.dto.question;

import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.model.enumeration.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDto {
    private Long id;
    private String content;
    private LocalDate examDate;
    private List<Category> categories;
    private List<AnswerResponseDto> answers;
}
