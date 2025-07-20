package com.movieflix.controller;

import com.movieflix.entity.Category;
import com.movieflix.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {
   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories(){
       return categoryService.findAll();
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id){
       return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
       categoryService.deleteById(id);
    }
}
