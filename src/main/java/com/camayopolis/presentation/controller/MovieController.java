package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.MovieDetailedDto;
import com.camayopolis.presentation.dto.MovieDto;
import com.camayopolis.service.interfaces.IMovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getInTheaters")
    public ResponseEntity<List<MovieDto>> getMoviesInTheaters() {
        List<MovieDto> movies = movieService.getMoviesInTheaters();

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getUpcomingReleases")
    public ResponseEntity<List<MovieDto>> getUpcomingReleases() {
        List<MovieDto> movies = movieService.getUpcomingReleases();

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<MovieDto>> createMovie(@Valid @RequestBody MovieDto movieDTO) {
        Optional<MovieDto> createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Integer id, @Valid @RequestBody MovieDto movieDTO) {
        Optional<MovieDto> updatedMovie = movieService.updateMovie(id, movieDTO);

        return updatedMovie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        if (!movieService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMovieDetailed/{id}")
    public ResponseEntity<Optional<MovieDetailedDto>> getMovieDetailed(@PathVariable Integer id) {
        Optional<MovieDetailedDto> movieDetailed = movieService.getMovieWithDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(movieDetailed);
    }
}
