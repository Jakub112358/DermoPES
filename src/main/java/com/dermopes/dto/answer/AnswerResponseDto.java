package com.dermopes.dto.answer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDto {
    private Long id;
    private String content;
    private boolean correct;
}
