package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.MovieCategoryDto;
import java.util.List;

public interface IMovieCategoryService {
    // Obtener categorías asociadas a una película por su ID
    List<MovieCategoryDto> getCategoriesByMovieId(Integer movieId);

    // Obtener películas asociadas a una categoría por su ID
    List<MovieCategoryDto> getMoviesByCategoryId(Integer categoryId);
}
