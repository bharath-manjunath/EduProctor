package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    QuestionType findByTypeName(String typeName);
}
