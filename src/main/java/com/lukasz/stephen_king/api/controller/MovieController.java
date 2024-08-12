package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.buisness.MovieService;
import com.lukasz.stephen_king.domain.MovieDetailsDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tmdb")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<MovieDto>> getStephenKingMovies() {
        List<MovieDto> stephenKingMovies = movieService.getStephenKingMovies();
        return ResponseEntity.ok().body(stephenKingMovies);
    }

    @GetMapping("/movie/{id}")
    @CrossOrigin(origins = "*")
    public MovieDetailsDomain getMovieDetails(@PathVariable int id) {
        return movieService.getMovieDetails(id);
    }
}
