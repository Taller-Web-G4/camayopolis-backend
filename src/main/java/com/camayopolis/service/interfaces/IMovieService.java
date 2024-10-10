package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.MovieDetailedDto;
import com.camayopolis.presentation.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<MovieDto> getAllMovies();
    Optional<MovieDto> getMovieById(Integer id);
    boolean existsById(Integer id);
    Optional<MovieDto> createMovie(MovieDto movieDTO);
    Optional<MovieDto> updateMovie(Integer id, MovieDto movieDTO);
    void deleteMovie(Integer id);
    Optional<MovieDetailedDto> getMovieWithDetails(Integer movieId);
}
