package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.api.dto.mapper.MovieDtoMapper;
import com.lukasz.stephen_king.buisness.dao.MovieDao;
import com.lukasz.stephen_king.buisness.mapper.MovieMapper;
import com.lukasz.stephen_king.domain.MovieDomain;
import com.lukasz.stephen_king.infrastructure.movie.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieDao movieDao;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MovieDtoMapper movieDtoMapper;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void shouldReturnStephenKingMovies() {

        Movie movie1 = TestObjectFactory.createMovie1;
        Movie movie2 = TestObjectFactory.createMovie2;

        MovieDomain movieDomain1 = TestObjectFactory.createMovieDomain1;
        MovieDomain movieDomain2 = TestObjectFactory.createMovieDomain2;

        MovieDto movieDto1 = TestObjectFactory.createMovieDto1;
        MovieDto movieDto2 = TestObjectFactory.createMovieDto2;

        List<Movie> movies = List.of(movie1, movie2);
        List<MovieDomain> movieDomains = List.of(movieDomain1, movieDomain2);
        List<MovieDto> movieDtos = List.of(movieDto1, movieDto2);

        when(movieDao.getStephenKingMovies()).thenReturn(movies);
        when(movieMapper.mapToDomain(movies.get(0))).thenReturn(movieDomains.get(0));
        when(movieMapper.mapToDomain(movies.get(1))).thenReturn(movieDomains.get(1));
        when(movieDtoMapper.mapToDto(movieDomains.get(0))).thenReturn(movieDtos.get(0));
        when(movieDtoMapper.mapToDto(movieDomains.get(1))).thenReturn(movieDtos.get(1));


        List<MovieDto> result = movieService.getStephenKingMovies();

        assertEquals(2, result.size());
    }

}