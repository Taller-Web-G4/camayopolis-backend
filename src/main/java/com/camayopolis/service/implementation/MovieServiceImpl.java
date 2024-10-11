package com.camayopolis.service.implementation;

import com.camayopolis.persistence.entity.CinemaEntity;
import com.camayopolis.persistence.entity.MovieEntity;
import com.camayopolis.persistence.entity.SessionEntity;
import com.camayopolis.persistence.repository.IMovieRepository;
import com.camayopolis.presentation.dto.MovieDetailedDto;
import com.camayopolis.presentation.dto.MovieDto;
import com.camayopolis.service.interfaces.IMovieService;
import com.camayopolis.util.mapper.IMovieMapper;
import com.camayopolis.persistence.repository.ISessionRepository;
import com.camayopolis.persistence.repository.ICinemaRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements IMovieService {
    private final IMovieRepository movieRepository;
    private final ISessionRepository sessionRepository;
    private final ICinemaRepository cinemaRepository;
    private final IMovieMapper movieMapper;

    public MovieServiceImpl(IMovieRepository movieRepository, IMovieMapper movieMapper, ICinemaRepository cinemaRepository, ISessionRepository sessionRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.sessionRepository = sessionRepository;
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

    @Override
    public Optional<MovieDetailedDto> getMovieWithDetails(Integer movieId){
        MovieEntity movieEntity = movieRepository.findById(movieId).orElse(null);
        List<SessionEntity> sessionEntities = sessionRepository.findByPel_Id(movieId);
        Map<Integer, List<SessionEntity>> sessionsByCinemaId = sessionEntities.stream().collect(Collectors.groupingBy(session -> session.getCin().getId()));
        List<MovieDetailedDto.CinemaDto> cinemaDtos = sessionsByCinemaId.entrySet().stream().map(entry -> {
            Integer cinemaId = entry.getKey();
            CinemaEntity cinemaEntity = cinemaRepository.findById(cinemaId).orElse(null);
            List<SessionEntity> sessionsForCinema = entry.getValue();
            List<MovieDetailedDto.FuncionDto> functions = sessionsForCinema.stream()
                    .map(session -> {
                        String formattedTime = session.getSesHoraInicio().format(DateTimeFormatter.ofPattern("hh:mm a"));
                        return new MovieDetailedDto.FuncionDto(List.of(formattedTime));
                    })
                    .toList();
            return new MovieDetailedDto.CinemaDto(cinemaEntity.getCinNombre(), cinemaEntity.getCinCiudad(), functions);
        }).toList();
        MovieDetailedDto movieDetailedDto = new MovieDetailedDto(
                movieEntity.getId(),
                movieEntity.getPelTitulo(),
                movieEntity.getPelSinopsis(),
                movieEntity.getPelFechaEstreno(),
                movieEntity.getPelDuracionMinutos(),
                movieEntity.getPelPosterUrl(),
                movieEntity.getPelTrailerUrl(),
                movieEntity.getPelEsEstrenoProximo(),
                movieEntity.getPelEsNuevoLanzamiento(),
                movieEntity.getPelEsPreventa(),
                cinemaDtos
        );
        return Optional.of(movieDetailedDto);
    }

}
