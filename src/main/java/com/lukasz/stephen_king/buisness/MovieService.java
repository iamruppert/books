package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.MovieDao;
import com.lukasz.stephen_king.buisness.mapper.MovieMapper;
import com.lukasz.stephen_king.domain.MovieDomain;
import com.lukasz.stephen_king.infrastructure.stephen_king.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
