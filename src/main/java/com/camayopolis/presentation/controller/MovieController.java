package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.MovieDTO;
import com.camayopolis.service.interfaces.IMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/get-by-id/{id}")
    public MovieDTO getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id).orElse(null);
    }
}
