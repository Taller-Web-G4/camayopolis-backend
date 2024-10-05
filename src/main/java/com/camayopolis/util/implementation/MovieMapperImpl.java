package com.camayopolis.util.implementation;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.presentation.dto.MovieDTO;
import com.camayopolis.util.mapper.IMovieMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MovieMapperImpl implements IMovieMapper {

    @Override
    public Optional<MovieDTO> toDTO(MovieEntity movieEntity) {
        if (movieEntity == null) {
            return Optional.empty();
        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setPelTitulo(movieEntity.getPelTitulo());
        movieDTO.setPelSinopsis(movieEntity.getPelSinopsis());
        movieDTO.setPelFechaEstreno(movieEntity.getPelFechaEstreno());
        movieDTO.setPelDuracionMinutos(movieEntity.getPelDuracionMinutos());
        movieDTO.setPelPosterUrl(movieEntity.getPelPosterUrl());
        movieDTO.setPelTrailerUrl(movieEntity.getPelTrailerUrl());
        movieDTO.setPelEsEstrenoProximo(movieEntity.getPelEsEstrenoProximo());
        movieDTO.setPelEsNuevoLanzamiento(movieEntity.getPelEsNuevoLanzamiento());
        movieDTO.setPelEsPreventa(movieEntity.getPelEsPreventa());

        return Optional.of(movieDTO);
    }

    @Override
    public Optional<MovieEntity> toEntity(MovieDTO movieDTO) {
        if (movieDTO == null) {
            return Optional.empty();
        }

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieDTO.getId());
        movieEntity.setPelTitulo(movieDTO.getPelTitulo());
        movieEntity.setPelSinopsis(movieDTO.getPelSinopsis());
        movieEntity.setPelFechaEstreno(movieDTO.getPelFechaEstreno());
        movieEntity.setPelDuracionMinutos(movieDTO.getPelDuracionMinutos());
        movieEntity.setPelPosterUrl(movieDTO.getPelPosterUrl());
        movieEntity.setPelTrailerUrl(movieDTO.getPelTrailerUrl());
        movieEntity.setPelEsEstrenoProximo(movieDTO.getPelEsEstrenoProximo());
        movieEntity.setPelEsNuevoLanzamiento(movieDTO.getPelEsNuevoLanzamiento());
        movieEntity.setPelEsPreventa(movieDTO.getPelEsPreventa());

        return Optional.of(movieEntity);
    }
}
