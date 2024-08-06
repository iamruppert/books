package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.stephen_king.Movie;
import com.lukasz.stephen_king.infrastructure.stephen_king.MovieDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieDao {

    List<Movie> getStephenKingMovies();

    Optional<MovieDetails> getMovieDetails(int movieId);
}
