package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Category;
import com.eduproctor.tut.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination,Long> {

    List<Examination> findByActiveTrue();

    List<Examination> findByCategory(Category category);

    List<Examination> findByCategoryAndActiveTrue(Category category);
}
