package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Category;
import com.eduproctor.tut.entity.Examination;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.CategoryRepository;
import com.eduproctor.tut.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService{

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Examination createExamination(Examination examination) {
        Category category = categoryRepository.findById(examination.getCategory().getCid())
                .orElseThrow(()->new ResourceNotFoundException("Category with id: "+examination.getCategory().getCid()+" not found"));
        examination.setCategory(category);

        return examinationRepository.save(examination);
    }

    @Override
    public Examination getExaminationById(Long id) {
        return examinationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Examination with id "+id+"not found"));
//        return examination;
    }

    @Override
    public List<Examination> getAllExamination() {
        return examinationRepository.findAll();
    }

    @Override
    public Examination updateExamination(Long id, Examination examination) {
        Examination existingExamination = examinationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Examination with id: "+id+"not found"));

        Category category = categoryRepository.findById(examination.getCategory().getCid())
                .orElseThrow(()->new ResourceNotFoundException("Category with id: "+examination.getCategory().getCid()+" not found"));

        existingExamination.setCategory(category);
        existingExamination.setTitle(examination.getTitle());
        existingExamination.setDescription(examination.getDescription());
//        existingExamination.setNoOfQuestions(examination.getNoOfQuestions());
        existingExamination.setActive(examination.isActive());
//        existingExamination.setMaxMarks(examination.getMaxMarks());
        existingExamination.setExaminationDate(examination.getExaminationDate());
//        existingExamination.setCategory(examination.getCategory());

        return examinationRepository.save(existingExamination);
    }

    @Override
    public void deleteExamination(Long id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Examination with id: "+id+" not found"));
        examinationRepository.delete(examination);
    }

    @Override
    public List<Examination> getActiveExaminations() {
        return examinationRepository.findByActiveTrue();
    }

    @Override
    public List<Examination> getExaminationsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category with category id: "+categoryId+" not found"));
        return examinationRepository.findByCategory(category);
    }

    @Override
    public List<Examination> getActiveExaminationsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: "+categoryId+" not found"));
        return examinationRepository.findByCategoryAndActiveTrue(category);
    }
}
