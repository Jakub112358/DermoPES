package com.dermopes.controller;

import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionCriteriaDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.service.QuestionService;
import com.dermopes.validation.existingQuestionId.ExistingQuestionId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.dermopes.config.ApiConstraints.*;

@RestController
@RequestMapping(BASE_URL)
@CrossOrigin(origins = ORIGIN)
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<QuestionResponseDto> save(@RequestBody @Valid QuestionCreateDto createDto) {
        QuestionResponseDto response = questionService.save(createDto);
        URI newResourceLocation = getNewResourceLocation(response.getId());
        return ResponseEntity.created(newResourceLocation).body(response);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponseDto>> findAll(){
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDto> findById(@PathVariable @ExistingQuestionId Long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }

    @PatchMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDto> update(@PathVariable @ExistingQuestionId Long id,
                                                      @RequestBody @Valid QuestionUpdateDto updateDto) {
        return ResponseEntity.ok(questionService.update(id, updateDto));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> delete(@PathVariable @ExistingQuestionId Long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/question-search")
    public ResponseEntity<List<QuestionResponseDto>> findByCriteria(@RequestBody QuestionCriteriaDto criteria){
        return ResponseEntity.ok(questionService.findByCriteria(criteria));
    }

    private URI getNewResourceLocation(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
