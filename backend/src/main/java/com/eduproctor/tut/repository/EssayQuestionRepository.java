package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.EssayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayQuestionRepository extends JpaRepository<EssayQuestion, Long> {
}
