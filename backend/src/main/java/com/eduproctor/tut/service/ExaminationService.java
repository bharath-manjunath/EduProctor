package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Examination;

import java.util.List;

public interface ExaminationService {
    public Examination createExamination(Examination examination);

    public Examination getExaminationById(Long id);

    public List<Examination> getAllExamination();

    public Examination updateExamination(Long id, Examination examination);

    public void deleteExamination(Long id);

    public List<Examination> getActiveExaminations();

    public List<Examination> getExaminationsByCategoryId(Long categoryId);

    public List<Examination> getActiveExaminationsByCategoryId(Long categoryId);
}
