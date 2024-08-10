package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.MovieDao;
import com.lukasz.stephen_king.buisness.mapper.MovieMapper;
import com.lukasz.stephen_king.domain.MovieDetailsDomain;
import com.lukasz.stephen_king.domain.MovieDomain;
import com.lukasz.stephen_king.domain.exception.NotFoundException;
import com.lukasz.stephen_king.infrastructure.stephen_king.CastMember;
import com.lukasz.stephen_king.infrastructure.stephen_king.Movie;
import com.lukasz.stephen_king.infrastructure.stephen_king.MovieDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieDao movieDao;
    private final MovieMapper movieMapper;


    public List<MovieDomain> getStephenKingMovies() {
        List<Movie> movies = movieDao.getStephenKingMovies();
        return movies.stream()
                .map(movieMapper::mapToDomain)
                .toList();

    }

    public MovieDetailsDomain getMovieDetails(int id) {
        Optional<MovieDetails> movieDetails = movieDao.getMovieDetails(id);
        if (movieDetails.isEmpty()) {
            throw new NotFoundException("Cannot find movie with id: [%s]".formatted(id));
        } else {
            ArrayList<CastMember> movieCast = movieDao.getMovieCast(id).get();
            return movieMapper.mapToDomain(movieDetails.get()).withCast(movieCast);
        }
    }
}
