package com.movieflix.service;


import com.movieflix.dto.CategoryDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::map)
                .collect(Collectors.toList());
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
     Category newCategory = categoryMapper.map(categoryDTO);
     newCategory = categoryRepository.save(newCategory);
     return categoryMapper.map(newCategory);

    }

    public CategoryDTO getCategoryById(Long id){
        Optional<Category> categoriesById = categoryRepository.findById(id);
        return categoriesById.map(categoryMapper::map).orElse(null);

    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO){
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            Category updatedCategory = categoryMapper.map(categoryDTO);
            updatedCategory.setId(id);
            Category categorySaved = categoryRepository.save(updatedCategory);
            return categoryMapper.map(categorySaved);
        }
        return null;
    }
}
