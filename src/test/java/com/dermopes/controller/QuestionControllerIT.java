package com.dermopes.controller;

import com.dermopes.BaseIT;
import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionResponseDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.util.QuestionFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.dermopes.config.ApiConstraints.QUESTION;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerIT extends BaseIT {
    @BeforeEach
    void cleanTables() {
        dbOperations.cleanQuestionTable();
    }

    @Test
    public void whenSave_thenResponseCreated() throws Exception {
        //given
        var request = QuestionFactory.getSimpleQuestionCreateDtoBuilder().build();

        //when
        var result = requestSender.sendPostRequest(QUESTION, toJsonString(request));

        //then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(Matchers.greaterThan(0)));

        //and
        expectSimpleQuestion(result);
    }

    @ParameterizedTest
    @MethodSource("questionCreateIncorrectParameters")
    public void whenSave_thenValidationFailed(QuestionCreateDto createDto) throws Exception {
        //when
        var result = requestSender.sendPostRequest(QUESTION, toJsonString(createDto));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void whenFindById_thenCorrectAnswer() throws Exception {
        //given
        var question = dbOperations.addSimpleQuestionToDb();

        //and
        var path = QUESTION + "/" + question.getId();

        //when
        var result = requestSender.sendGetRequest(path);

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(question.getId()));

        //and
        expectSimpleQuestion(result);
    }

    @Test
    public void whenFindById_thenNotFound() throws Exception {
        //given
        dbOperations.addSimpleQuestionToDb();

        //and
        var path = QUESTION + "/" + Long.MAX_VALUE;

        //when
        var result = requestSender.sendGetRequest(path);

        //then
        result.andExpect(status().isNotFound());
    }

    @Test
    public void whenFindAll_thenCorrectAnswer() throws Exception {
        //given
        dbOperations.addSimpleQuestionToDb();
        dbOperations.addSimpleQuestionToDb();

        //when
        var result = requestSender.sendGetRequest(QUESTION);

        //then
        result.andExpect(status().isOk());

        //and
        var responseJson = result.andReturn().getResponse().getContentAsString();
        Set<QuestionResponseDto> questions = objectMapper.readValue(responseJson, new TypeReference<>() {
        });

        assertEquals(2, questions.size());
    }

    @Test
    public void whenUpdate_thenCorrectAnswer() throws Exception {
        //given
        var question = dbOperations.addSimpleQuestionToDb();
        var path = QUESTION + "/" + question.getId();

        //and
        var updateDto = QuestionFactory.getQuestionUpdateDtoBuilder().build();

        //when
        var result = requestSender.sendPatchRequest(path, toJsonString(updateDto));

        //then
        result.andExpect(status().isOk());

        //and
        expectChangedQuestion(result);
    }

    @ParameterizedTest
    @MethodSource("questionUpdateIncorrectParameters")
    public void whenUpdate_thenValidationFailed(QuestionUpdateDto updateDto) throws Exception {
        //given
        var question = dbOperations.addSimpleQuestionToDb();
        var path = QUESTION + "/" + question.getId();

        //when
        var result = requestSender.sendPatchRequest(path, toJsonString(updateDto));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void whenDelete_thenCorrectAnswer() throws Exception {
        //given
        var question1 = dbOperations.addSimpleQuestionToDb();
        var question2 = dbOperations.addSimpleQuestionToDb();

        //and
        var path1 = QUESTION + "/" + question1.getId();

        //when
        var result = requestSender.sendDeleteRequest(path1);

        //then
        result.andExpect(status().isNoContent());

        //and
        assertFalse(questionRepository.existsById(question1.getId()));
        assertTrue(questionRepository.existsById(question2.getId()));
    }

    @Test
    public void whenDelete_thenNotFound() throws Exception {
        //given
        dbOperations.addSimpleQuestionToDb();

        //and
        var path = QUESTION + "/" + Long.MAX_VALUE;

        //when
        var result = requestSender.sendDeleteRequest(path);

        //then
        result.andExpect(status().isNotFound());
    }

    private void expectChangedQuestion(ResultActions result) throws Exception {
        var responseJson = result.andReturn().getResponse().getContentAsString();
        QuestionResponseDto responseDto = objectMapper.readValue(responseJson, new TypeReference<>() {
        });

        assertEquals(QuestionFactory.changedContent, responseDto.getContent());
        assertEquals(QuestionFactory.changedExamDate, responseDto.getExamDate());
        assertEquals(QuestionFactory.changedDifficulty, responseDto.getDifficulty());
        assertEquals(QuestionFactory.changedCategories, responseDto.getCategories());
        assertEquals(QuestionFactory.simpleAnswerResponseDtos, responseDto.getAnswers());
    }


    private void expectSimpleQuestion(ResultActions result) throws Exception {
        var responseJson = result.andReturn().getResponse().getContentAsString();
        QuestionResponseDto responseDto = objectMapper.readValue(responseJson, new TypeReference<>() {
        });

        assertEquals(QuestionFactory.simpleContent, responseDto.getContent());
        assertEquals(QuestionFactory.simpleExamDate, responseDto.getExamDate());
        assertEquals(QuestionFactory.simpleDifficulty, responseDto.getDifficulty());
        assertEquals(QuestionFactory.simpleCategories, responseDto.getCategories());
        assertEquals(QuestionFactory.simpleAnswerResponseDtos, responseDto.getAnswers());
    }

    private static Stream<Arguments> questionCreateIncorrectParameters() {
        return Stream.of(
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().content(null).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().content(" ").build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().examDate(LocalDate.of(2010, 1, 1)).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().examDate(LocalDate.now().plusDays(1)).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().examDate(null).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().difficulty(null).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().answers(List.of(new AnswerCreateDto("content", null))).build()),
                Arguments.of(QuestionFactory.getSimpleQuestionCreateDtoBuilder().answers(List.of(new AnswerCreateDto(" ", true))).build())
        );
    }

    private static Stream<Arguments> questionUpdateIncorrectParameters() {
        return Stream.of(
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().content(null).build()),
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().content(" ").build()),
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().examDate(LocalDate.of(2010, 1, 1)).build()),
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().examDate(LocalDate.now().plusDays(1)).build()),
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().examDate(null).build()),
                Arguments.of(QuestionFactory.getQuestionUpdateDtoBuilder().difficulty(null).build())
        );
    }


}
