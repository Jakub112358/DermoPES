package com.dermopes.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricService {
    private final Map<String, Map<Integer, Integer>> metricMap;

    public MetricService() {
        metricMap = new ConcurrentHashMap<>();
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

    public Map<String, Map<Integer, Integer>> getFullMetric() {
        return metricMap;
    }
}
