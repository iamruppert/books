package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.MovieDetailsDto;
import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.api.dto.mapper.MovieDtoMapper;
import com.lukasz.stephen_king.buisness.dao.MovieDao;
import com.lukasz.stephen_king.buisness.mapper.CastMemberMapper;
import com.lukasz.stephen_king.buisness.mapper.MovieMapper;
import com.lukasz.stephen_king.domain.CastMemberDomain;
import com.lukasz.stephen_king.domain.MovieDetailsDomain;
import com.lukasz.stephen_king.domain.MovieDomain;
import com.lukasz.stephen_king.domain.exception.NotFoundException;
import com.lukasz.stephen_king.infrastructure.movie.CastMember;
import com.lukasz.stephen_king.infrastructure.movie.Movie;
import com.lukasz.stephen_king.infrastructure.movie.MovieDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieDao movieDao;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MovieDtoMapper movieDtoMapper;

    @Mock
    private CastMemberMapper castMemberMapper;

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

    @Test
    public void shouldReturnMovieDetails() {

        int movieId = 694;
        MovieDetails movieDetails = TestObjectFactory.movieDetails1;
        ArrayList<CastMember> castMembers = new ArrayList<>();
        castMembers.add(TestObjectFactory.castMember1);
        castMembers.add(TestObjectFactory.castMember2);

        MovieDetailsDomain movieDetailsDomain = TestObjectFactory.movieDetailsDomain1;
        ArrayList<CastMemberDomain> castMemberDomains = new ArrayList<>();
        castMemberDomains.add(TestObjectFactory.castMemberDomain1);
        castMemberDomains.add(TestObjectFactory.castMemberDomain2);
        movieDetailsDomain.setCast(castMemberDomains);

        MovieDetailsDto movieDetailsDto = TestObjectFactory.movieDetailsDto1;

        when(movieDao.getMovieDetails(movieId)).thenReturn(Optional.of(movieDetails));
        when(movieDao.getMovieCast(movieId)).thenReturn(Optional.of(castMembers));
        when(movieMapper.mapToDomain(movieDetails)).thenReturn(movieDetailsDomain);
        when(castMemberMapper.mapToDomain(castMembers.get(0))).thenReturn(castMemberDomains.get(0));
        when(castMemberMapper.mapToDomain(castMembers.get(1))).thenReturn(castMemberDomains.get(1));
        when(movieDtoMapper.mapToDto(movieDetailsDomain)).thenReturn(movieDetailsDto);

        MovieDetailsDto result = movieService.getMovieDetails(movieId);

        assertEquals(movieDetailsDto, result);
        assertEquals(2, result.getCast().size());

  }

    @Test
    public void shouldThrowNotFoundExceptionWhenMovieNotFound() {

        int movieId = 999;

        when(movieDao.getMovieDetails(movieId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> movieService.getMovieDetails(movieId));

        assertEquals("Cannot find movie with id: [999]", exception.getMessage());
    }

}