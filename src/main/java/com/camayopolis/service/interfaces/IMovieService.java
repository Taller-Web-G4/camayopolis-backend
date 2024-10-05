package com.camayopolis.service.interfaces;

import com.camayopolis.presentation.dto.MovieDTO;

import java.util.Optional;

public interface IMovieService {
    Optional<MovieDTO> getMovieById(Integer id);
    boolean existsById(Integer id);
    Optional<MovieDTO> createMovie(MovieDTO movieDTO);
    Optional<MovieDTO> updateMovie(Integer id, MovieDTO movieDTO);
    void deleteMovie(Integer id);
}
