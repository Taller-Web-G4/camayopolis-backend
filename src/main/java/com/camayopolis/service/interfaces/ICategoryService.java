package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Integer id);
    boolean existsById(Integer id);
    Optional<CategoryDto> createCategory(CategoryDto categoryDto);
    Optional<CategoryDto> updateCategory(Integer id, CategoryDto categoryDto);
    void deleteCategory(Integer id);
}
