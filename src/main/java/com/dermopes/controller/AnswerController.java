package com.dermopes.controller;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerResponseDto> save(@PathVariable Long questionId, @RequestBody AnswerCreateDto createDto) {
        AnswerResponseDto response = answerService.save(questionId, createDto);
        URI newResourceLocation = getNewResourceLocation(response.getId());
        return ResponseEntity.created(newResourceLocation).body(response);
    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerResponseDto>> findAll(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.findAll(questionId));
    }

    @GetMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<AnswerResponseDto> findById(@PathVariable Long questionId, @PathVariable Long answerId) {
        return ResponseEntity.ok(answerService.findById(questionId, answerId));
    }

    private URI getNewResourceLocation(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
