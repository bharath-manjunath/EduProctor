package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
