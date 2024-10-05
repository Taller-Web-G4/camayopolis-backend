package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.persistence.repository.IMovieRepository;
import com.camayopolis.presentation.dto.MovieDTO;
import com.camayopolis.service.interfaces.IMovieService;
import com.camayopolis.util.mapper.IMovieMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {
    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    public MovieServiceImpl(IMovieRepository movieRepository, IMovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Optional<MovieDTO> getMovieById(Integer id) {
        Optional<MovieEntity> movieEntity = this.movieRepository.findById(id);
        if (movieEntity.isEmpty()) {
            return Optional.empty();
        }

        return movieMapper.toDTO(movieEntity.get());
    }

    @Override
    public boolean existsById(Integer id) {
        return this.movieRepository.existsById(id);
    }

    @Override
    public Optional<MovieDTO> createMovie(MovieDTO movieDTO) {
        Optional<MovieEntity> movieEntity = movieMapper.toEntity(movieDTO);
        if (movieEntity.isEmpty()) {
            return Optional.empty();
        }
        MovieEntity savedEntity = movieRepository.save(movieEntity.get());
        return movieMapper.toDTO(savedEntity);
    }

    @Override
    public Optional<MovieDTO> updateMovie(Integer id, MovieDTO movieDTO) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        Optional<MovieEntity> movieEntity = movieMapper.toEntity(movieDTO);
        if (movieEntity.isEmpty()) {
            return Optional.empty();
        }

        movieEntity.get().setId(id);
        MovieEntity updatedEntity = movieRepository.save(movieEntity.get());
        return movieMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteMovie(Integer id) {
        this.movieRepository.deleteById(id);
    }
}
