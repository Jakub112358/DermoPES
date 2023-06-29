package com.dermopes.service;

import com.dermopes.dto.metric.MetricResponseDto;
import com.dermopes.service.util.MetricMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricService {
    private final Map<String, Map<Integer, Integer>> metricMap;
    private final MetricMapper metricMapper;

    public MetricService(MetricMapper metricMapper) {
        metricMap = new ConcurrentHashMap<>();
        this.metricMapper = metricMapper;
    }

    public void increaseCount(String request, int statusCode) {
        Map<Integer, Integer> statusCodeMap = metricMap.get(request);
        if (statusCodeMap == null) {
            statusCodeMap = new ConcurrentHashMap<>();
        }

        Integer count = statusCodeMap.get(statusCode);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusCodeMap.put(statusCode, count);
        metricMap.put(request, statusCodeMap);
    }

    public Set<MetricResponseDto> getFullMetric() {
        return metricMapper.toResponseDto(metricMap);
    }


}
