package com.camayopolis.util.mapper;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.presentation.dto.MovieDTO;

import java.util.Optional;

public interface IMovieMapper {
    Optional<MovieDTO> toDTO(MovieEntity movieEntity);
    Optional<MovieEntity> toEntity(MovieDTO movieDTO);
}
