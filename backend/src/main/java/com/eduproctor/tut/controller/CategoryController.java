package com.eduproctor.tut.controller;

import com.eduproctor.tut.entity.Category;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
       try{
           categoryService.deleteCategory(id);
           return ResponseEntity.ok("Category with id "+id+"deleted successfully");
       }catch (ResourceNotFoundException ex){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(id,category));
    }


}
