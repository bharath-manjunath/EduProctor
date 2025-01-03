package com.eduproctor.tut.service;

import com.eduproctor.tut.dto.QuestionDTO;
import com.eduproctor.tut.entity.Question;

import java.util.List;

public interface QuestionService {
    public Question createQuestion(QuestionDTO questionDTO);

    public List<Question> getAllQuestions();

    public Question getQuestionById(Long id);

    public List<Question> getQuestionByExamination(Long eid);

    public void deleteQuestion(Long id);
}
