package com.eduproctor.tut.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private String content; // Common field for all questions
    private Long examinationId; // ID of the examination
    private Long typeId; // Question type (e.g., 1 for QuizQuestion, 2 for FillInTheBlanks)

    // QuizQuestion-specific fields
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String quizAnswer;

    // FillInTheBlanks-specific field
    private String fillAnswer;

    // ShortAnswerQuestion-specific field
    private String shortAnswer;

    // EssayQuestion-specific fields
    private String essayAnswer;
    private Integer wordLimit;
}
