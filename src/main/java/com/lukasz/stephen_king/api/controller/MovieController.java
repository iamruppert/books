package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.api.dto.MovieDetailsDto;
import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.buisness.MovieService;
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
    public ResponseEntity<MovieDetailsDto> getMovieDetails(@PathVariable int id) {
        MovieDetailsDto movieDetails = movieService.getMovieDetails(id);
        return ResponseEntity.ok().body(movieDetails);
    }
}
