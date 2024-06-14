package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.stephen_king.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieDao {

    List<Movie> getStephenKingMovies();

}
