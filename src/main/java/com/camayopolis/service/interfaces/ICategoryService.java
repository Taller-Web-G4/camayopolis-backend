package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Integer id);
    boolean existsById(Integer id);
    Optional<CategoryDto> createCategory(CategoryDto categoryDTO);
    Optional<CategoryDto> updateCategory(Integer id, CategoryDto categoryDTO);
    void deleteCategory(Integer id);
}
