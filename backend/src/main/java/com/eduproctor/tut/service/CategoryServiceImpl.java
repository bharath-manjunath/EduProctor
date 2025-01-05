package com.eduproctor.tut.service;

import com.eduproctor.tut.entity.Category;
import com.eduproctor.tut.error.CategoryNotFoundException;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return  categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: "+id+"not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id "+id+"Not found"));

        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: "+id+" not found"));
        existingCategory.setTitle(category.getTitle());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }
}
