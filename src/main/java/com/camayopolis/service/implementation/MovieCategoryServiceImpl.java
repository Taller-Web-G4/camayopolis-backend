package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.MovieCategoryEntity;
import com.camayopolis.persistence.repository.IMovieCategoryRepository;
import com.camayopolis.presentation.dto.MovieCategoryDto;
import com.camayopolis.service.interfaces.IMovieCategoryService;
import com.camayopolis.util.mapper.IMovieCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCategoryServiceImpl implements IMovieCategoryService {

    private final IMovieCategoryRepository movieCategoryRepository;
    private final IMovieCategoryMapper movieCategoryMapper;

    public MovieCategoryServiceImpl(IMovieCategoryRepository movieCategoryRepository, IMovieCategoryMapper movieCategoryMapper) {
        this.movieCategoryRepository = movieCategoryRepository;
        this.movieCategoryMapper = movieCategoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieCategoryDto> getCategoriesByMovieId(Integer movieId) {
        // Consulta basada en el ID de la película
        List<MovieCategoryEntity> movieCategories = movieCategoryRepository.findMovieCategoryEntitiesByPelId(movieId);

        // Convertir entidades a DTOs
        return movieCategories.stream()
                .map(movieCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieCategoryDto> getMoviesByCategoryId(Integer categoryId) {
        // Consulta basada en el ID de la categoría
        List<MovieCategoryEntity> movieCategories = movieCategoryRepository.findMovieCategoryEntitiesByCatId(categoryId);

        // Convertir entidades a DTOs
        return movieCategories.stream()
                .map(movieCategoryMapper::toDto)
                .collect(Collectors.toList());
    }
}