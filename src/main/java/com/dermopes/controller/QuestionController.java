package com.dermopes.controller;

import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.dermopes.config.ApiConstraints.ORIGIN;
import static com.dermopes.config.ApiConstraints.QUESTION;

@RestController
@RequestMapping(QUESTION)
@CrossOrigin(origins = ORIGIN)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponseDto> save(@RequestBody QuestionCreateDto createDto) {
        QuestionResponseDto response = questionService.save(createDto);
        URI newResourceLocation = getNewResourceLocation(response.getId());
        return ResponseEntity.created(newResourceLocation).body(response);

    }

    private URI getNewResourceLocation(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
