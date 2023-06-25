package com.dermopes.util;

import com.dermopes.dto.answer.AnswerCreateDto;
import com.dermopes.dto.answer.AnswerResponseDto;
import com.dermopes.dto.question.QuestionCreateDto;
import com.dermopes.dto.question.QuestionUpdateDto;
import com.dermopes.model.Answer;
import com.dermopes.model.Question;
import com.dermopes.model.enumeration.Category;
import com.dermopes.model.enumeration.Difficulty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.dermopes.model.enumeration.Category.*;
import static com.dermopes.model.enumeration.Difficulty.EXTREME;
import static com.dermopes.model.enumeration.Difficulty.MEDIUM;

public class QuestionFactory {
    public static String simpleContent = "simple content";
    public static LocalDate simpleExamDate = LocalDate.of(2020, 1, 1);
    public static Difficulty simpleDifficulty = MEDIUM;
    public static List<Category> simpleCategories = List.of(GENERAL);
    public static List<Answer> simpleAnswers = new ArrayList<>();
    public static List<AnswerCreateDto> simpleAnswerCreateDtos = new ArrayList<>();
    public static List<AnswerResponseDto> simpleAnswerResponseDtos = new ArrayList<>();
    public static String changedContent = "changed content";
    public static LocalDate changedExamDate = LocalDate.of(2019, 10, 10);
    public static Difficulty changedDifficulty = EXTREME;
    public static List<Category> changedCategories = List.of(TRICHOSCOPY, DERMOSCOPY);

    public static QuestionCreateDto.QuestionCreateDtoBuilder getSimpleQuestionCreateDtoBuilder() {
        return QuestionCreateDto.builder()
                .content(simpleContent)
                .examDate(simpleExamDate)
                .difficulty(simpleDifficulty)
                .categories(simpleCategories)
                .answers(simpleAnswerCreateDtos);
    }

    public static Question.QuestionBuilder getSimpleQuestionBuilder() {
        return Question.builder()
                .content(simpleContent)
                .examDate(simpleExamDate)
                .difficulty(simpleDifficulty)
                .categories(simpleCategories)
                .answers(simpleAnswers);
    }

    public static QuestionUpdateDto.QuestionUpdateDtoBuilder getQuestionUpdateDtoBuilder() {
        return QuestionUpdateDto.builder()
                .content(changedContent)
                .examDate(changedExamDate)
                .difficulty(changedDifficulty)
                .categories(changedCategories);
    }

}
