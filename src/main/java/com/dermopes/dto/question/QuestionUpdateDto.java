package com.dermopes.dto.question;

import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;
import com.dermopes.validation.isAfter.IsAfter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionUpdateDto {
    @NotBlank
    private String content;

    //assuming that 2010-01-01 is the date of the first PES exam
    @NotNull
    @IsAfter(current = "2010-01-01")
    @PastOrPresent
    private LocalDate examDate;
    @NotNull
    private Difficulty difficulty;
    private List<Category> categories;
}
