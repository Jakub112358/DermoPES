package com.dermopes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerCreateDto {
    private String content;
    private boolean correct;
}
