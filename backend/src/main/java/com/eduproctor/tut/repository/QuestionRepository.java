package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByExaminationEid(Long eid);
}
