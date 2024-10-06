package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.persistence.repository.IMovieRepository;
import com.camayopolis.presentation.dto.MovieDto;
import com.camayopolis.service.interfaces.IMovieService;
import com.camayopolis.util.mapper.IMovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> movieEntities = this.movieRepository.findAll();
        return movieMapper.toDto(movieEntities);
    }

    @Override
    public Optional<MovieDto> getMovieById(Integer id) {
        Optional<MovieEntity> movieEntity = this.movieRepository.findById(id);
        return movieEntity.map(movieMapper::toDto);

    }

    @Override
    public boolean existsById(Integer id) {
        return this.movieRepository.existsById(id);
    }

    @Override
    public Optional<MovieDto> createMovie(MovieDto movieDTO) {
        MovieEntity movieEntity = movieMapper.toEntity(movieDTO);
        MovieEntity savedEntity = movieRepository.save(movieEntity);
        return Optional.of(movieMapper.toDto(savedEntity));
    }

    @Override
    public Optional<MovieDto> updateMovie(Integer id, MovieDto movieDTO) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        MovieEntity existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie == null) {
            return Optional.empty();
        }

        movieMapper.partialUpdate(movieDTO, existingMovie);
        MovieEntity updatedEntity = movieRepository.save(existingMovie);

        return Optional.of(movieMapper.toDto(updatedEntity));
    }

    @Override
    public void deleteMovie(Integer id) {
        this.movieRepository.deleteById(id);
    }
}
