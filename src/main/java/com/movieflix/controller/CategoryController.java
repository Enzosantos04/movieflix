package com.movieflix.controller;

import com.movieflix.dto.CategoryDTO;

import com.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO category){
        CategoryDTO categoryDTO = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
       if(categoryService.getCategoryById(id) != null){
           CategoryDTO categoryDTO = categoryService.getCategoryById(id);
           return ResponseEntity.ok(categoryDTO);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
        if(categoryService.getCategoryById(id) != null){
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
        }
    }
}
