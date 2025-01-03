package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.FillInTheBlanksQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FillInTheBlanksQuestionRepository extends JpaRepository<FillInTheBlanksQuestion, Long> {
}
