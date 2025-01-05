package com.eduproctor.tut.controller;

import com.eduproctor.tut.dto.QuestionDTO;
import com.eduproctor.tut.entity.Question;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.error.RestResponseEntityExceptionHandler;
import com.eduproctor.tut.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDTO questionDTO){
        Question savedQuestion = questionService.createQuestion(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }


    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        List<Question> questionList = questionService.getAllQuestions();
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        Question question = questionService.getQuestionById(id);
        if(question == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(question);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/examination/{eid}")
    public ResponseEntity<List<Question>> getQuestionByExamination(@PathVariable Long eid){
        return ResponseEntity.ok(questionService.getQuestionByExamination(eid));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question with id: "+id+" deleted successfully");
    }
}
