package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public void deleteCategory(Long id);

    public Category updateCategory(Long id, Category category);
}
