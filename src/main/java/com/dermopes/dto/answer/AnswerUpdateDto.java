package com.dermopes.dto.answer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerUpdateDto {
    private String content;
    private boolean correct;
}
