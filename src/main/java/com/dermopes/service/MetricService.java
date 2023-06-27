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

    public void increaseCount(String request, int status) {
        Map<Integer, Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        metricMap.put(request, statusMap);
    }

    public Set<MetricResponseDto> getFullMetric() {
        return metricMapper.toResponseDto(metricMap);
    }


}
