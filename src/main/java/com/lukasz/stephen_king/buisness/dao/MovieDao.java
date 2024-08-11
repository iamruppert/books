package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.movie.CastMember;
import com.lukasz.stephen_king.infrastructure.movie.Movie;
import com.lukasz.stephen_king.infrastructure.movie.MovieDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface MovieDao {

    List<Movie> getStephenKingMovies();

    Optional<MovieDetails> getMovieDetails(int movieId);

    Optional<ArrayList<CastMember>> getMovieCast(Integer movieId);
}
