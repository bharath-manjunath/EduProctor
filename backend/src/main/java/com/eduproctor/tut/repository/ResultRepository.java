package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findTopByOrderBySubmitDateTimeDesc();
    List<Result> findByClientId(Long clientId);
    void deleteByClientId(Long clientId);
}
