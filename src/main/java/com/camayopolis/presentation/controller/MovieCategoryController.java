package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.MovieCategoryDto;
import com.camayopolis.service.interfaces.IMovieCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-category")
public class MovieCategoryController {

    private final IMovieCategoryService movieCategoryService;

    public MovieCategoryController(IMovieCategoryService movieCategoryService) {
        this.movieCategoryService = movieCategoryService;
    }

    @GetMapping("/categories-by-movie/{movieId}")
    public ResponseEntity<List<MovieCategoryDto>> getCategoriesByMovie(@PathVariable Integer movieId) {
        List<MovieCategoryDto> categories = movieCategoryService.getCategoriesByMovieId(movieId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/movies-by-category/{categoryId}")
    public ResponseEntity<List<MovieCategoryDto>> getMoviesByCategory(@PathVariable Integer categoryId) {
        List<MovieCategoryDto> movies = movieCategoryService.getMoviesByCategoryId(categoryId);
        return ResponseEntity.ok(movies);
    }
}
