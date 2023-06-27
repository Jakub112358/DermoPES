package com.dermopes.controller;

import com.dermopes.dto.metric.MetricResponseDto;
import com.dermopes.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.dermopes.config.ApiConstraints.ORIGIN;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = ORIGIN)
public class MetricController {

    private final MetricService metricService;

    @GetMapping(value = "/metric")
    @ResponseBody
    public ResponseEntity<Set<MetricResponseDto>> getStatusMetric() {
        return ResponseEntity.ok(metricService.getFullMetric());
    }
}
