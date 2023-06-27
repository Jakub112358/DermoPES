package com.dermopes.service.util;

import com.dermopes.dto.metric.MetricResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MetricMapper {
    public Set<MetricResponseDto> toResponseDto(Map<String, Map<Integer, Integer>> metricMap) {
        Set<MetricResponseDto> result = new HashSet<>();
        for (Map.Entry<String, Map<Integer, Integer>> stringMapEntry : metricMap.entrySet()) {
            stringMapEntry.getValue().forEach((code, count) -> result.add(new MetricResponseDto(stringMapEntry.getKey(), code, count)));
        }
        return result;
    }

}
