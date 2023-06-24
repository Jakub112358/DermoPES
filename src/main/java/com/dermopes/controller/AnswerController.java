package com.dermopes.controller;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.service.AnswerService;
import com.dermopes.validation.existingAnswerId.ExistingAnswerId;
import com.dermopes.validation.existingQuestionId.ExistingQuestionId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.dermopes.config.ApiConstraints.ORIGIN;
import static com.dermopes.config.ApiConstraints.QUESTION;

@RestController
@RequestMapping(QUESTION)
@CrossOrigin(origins = ORIGIN)
@RequiredArgsConstructor
@Validated
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerResponseDto> save(@PathVariable @ExistingQuestionId Long questionId,
                                                  @RequestBody @Valid AnswerCreateDto createDto) {
        AnswerResponseDto response = answerService.save(questionId, createDto);
        URI newResourceLocation = getNewResourceLocation(response.getId());
        return ResponseEntity.created(newResourceLocation).body(response);
    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerResponseDto>> findAll(@PathVariable @ExistingQuestionId Long questionId) {
        return ResponseEntity.ok(answerService.findAll(questionId));
    }

    @GetMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<AnswerResponseDto> findById(@PathVariable @ExistingQuestionId Long questionId,
                                                      @PathVariable @ExistingAnswerId Long answerId) {
        return ResponseEntity.ok(answerService.findById(questionId, answerId));
    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<AnswerResponseDto> update(@PathVariable @ExistingQuestionId Long questionId,
                                                    @PathVariable @ExistingAnswerId Long answerId,
                                                    @RequestBody @Valid AnswerCreateDto createDto) {
        return ResponseEntity.ok(answerService.update(questionId, answerId, createDto));
    }

    @DeleteMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<Void> delete(@PathVariable @ExistingQuestionId Long questionId,
                                       @PathVariable @ExistingAnswerId Long answerId) {
        answerService.deleteById(questionId, answerId);
        return ResponseEntity.noContent().build();
    }

    private URI getNewResourceLocation(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
