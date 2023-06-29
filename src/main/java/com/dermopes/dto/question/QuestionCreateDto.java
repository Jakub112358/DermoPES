package com.dermopes.dto.question;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;
import com.dermopes.validation.isAfter.IsAfter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static com.dermopes.config.Constants.FIRST_EXAM_DATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCreateDto {
    @NotBlank
    private String content;
    @NotNull
    @IsAfter(current = FIRST_EXAM_DATE)
    @PastOrPresent
    private LocalDate examDate;
    @NotNull
    private Difficulty difficulty;
    @Valid
    private List<AnswerCreateDto> answers;
    private List<Category> categories;

}
