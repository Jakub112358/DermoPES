package com.dermopes.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerCreateDto {
    @NotBlank
    private String content;
    @NotNull
    private Boolean correct;
}
