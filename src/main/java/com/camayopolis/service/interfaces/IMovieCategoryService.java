package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.MovieCategoryDto;
import java.util.List;

public interface IMovieCategoryService {
    List<MovieCategoryDto> getCategoriesByMovieId(Integer movieId);

    List<MovieCategoryDto> getMoviesByCategoryId(Integer categoryId);
}
