package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.CategoryEntity;
import com.camayopolis.persistence.repository.ICategoryRepository;
import com.camayopolis.presentation.dto.CategoryDto;
import com.camayopolis.service.interfaces.ICategoryService;
import com.camayopolis.util.mapper.ICategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    public CategoryServiceImpl(ICategoryRepository categoryRepository, ICategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAll();
        return categoryMapper.toDto(categoryEntities);
    }

    public Optional<CategoryDto> getCategoryById(Integer id) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(id);
        return categoryEntity.map(categoryMapper::toDto);
    }

    public boolean existsById(Integer id) {
        return this.categoryRepository.existsById(id);
    }

    public Optional<CategoryDto> createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = this.categoryMapper.toEntity(categoryDto);
        CategoryEntity savedEntity = this.categoryRepository.save(categoryEntity);
        return Optional.of(this.categoryMapper.toDto(savedEntity));
    }

    public Optional<CategoryDto> updateCategory(Integer id, CategoryDto categoryDto) {
        if (!this.categoryRepository.existsById(id)) {
            return Optional.empty();
        }

        CategoryEntity existingCategory = this.categoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            return Optional.empty();
        }

        this.categoryMapper.partialUpdate(categoryDto, existingCategory);

        CategoryEntity updatedEntity = this.categoryRepository.save(existingCategory);

        return Optional.of(this.categoryMapper.toDto(updatedEntity));
    }


    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteById(id);
    }
}
