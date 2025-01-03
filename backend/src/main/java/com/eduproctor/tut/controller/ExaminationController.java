package com.eduproctor.tut.controller;


import com.eduproctor.tut.entity.Examination;
import com.eduproctor.tut.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examinations")
public class ExaminationController {


    @Autowired
    private ExaminationService examinationService;

    @PostMapping
    public ResponseEntity<Examination> createExamination(@RequestBody Examination examination){
        Examination createdExamination = examinationService.createExamination(examination);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExamination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examination> getExaminationById(@PathVariable Long id){
        Examination examination = examinationService.getExaminationById(id);
        return ResponseEntity.ok(examination);
    }

    @GetMapping
    public ResponseEntity<List<Examination>> getAllExamination(){
        List<Examination> examinations = examinationService.getAllExamination();
        return ResponseEntity.ok(examinations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examination> updateExamination(@PathVariable Long id,@RequestBody Examination examination){
        Examination updatedExamination = examinationService.updateExamination(id,examination);
        return ResponseEntity.ok(updatedExamination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExamination(@PathVariable Long id){
        examinationService.deleteExamination(id);
        return ResponseEntity.ok("Examination with id "+id+" delete successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<List<Examination>> getActiveExaminations(){
        List<Examination> activeExaminations = examinationService.getActiveExaminations();
        return ResponseEntity.ok(activeExaminations);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Examination>> getExaminationsByCategoryId(@PathVariable Long categoryId){
        List<Examination> examinations = examinationService.getExaminationsByCategoryId(categoryId);
        return ResponseEntity.ok(examinations);
    }


    @GetMapping("/category/{categoryId}/active")
    public ResponseEntity<List<Examination>> getActiveExaminationsByCategoryId(@PathVariable Long categoryId){
        List<Examination> examinations = examinationService.getActiveExaminationsByCategoryId(categoryId);
        return ResponseEntity.ok(examinations);
    }


}
