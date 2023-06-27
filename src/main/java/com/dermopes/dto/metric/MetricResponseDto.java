package com.dermopes.dto.metric;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricResponseDto {
    private String request;
    private Integer code;
    private Integer count;

}
