package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.CategoryDto;
import com.camayopolis.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id).orElse(null));
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/update/{id}")
    public CategoryDto updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
