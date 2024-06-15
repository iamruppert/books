package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.buisness.MovieService;
import com.lukasz.stephen_king.domain.MovieDetailsDomain;
import com.lukasz.stephen_king.domain.MovieDomain;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tmdb")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieDomain> getStephenKingMovies() {
        return movieService.getStephenKingMovies();
    }

        @GetMapping("/movie/{id}")
    public MovieDetailsDomain getMovieDetails(@PathVariable int id) {
        return movieService.getMovieDetails(id);
    }
}
