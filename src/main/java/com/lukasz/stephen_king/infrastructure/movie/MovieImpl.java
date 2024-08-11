package com.lukasz.stephen_king.infrastructure.movie;

import com.lukasz.stephen_king.buisness.dao.MovieDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieImpl implements MovieDao {

    private static final int STEPHEN_KING_ID = 3027;

    @Value("${API_KEY}")
    private String API_KEY;

    private final WebClient tmdbWebClient;

    @Autowired
    public MovieImpl(@Qualifier("tmdbWebClient") WebClient tmdbWebClient) {
        this.tmdbWebClient = tmdbWebClient;
    }

    @Override
    public List<Movie> getStephenKingMovies() {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/person/{person_id}/movie_credits")
                        .build(STEPHEN_KING_ID))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                .retrieve()
                .bodyToMono(StephenKingMoviesResponse.class)
                .map(StephenKingMoviesResponse::getCrew)
                .block();
    }

    @Override
    public Optional<MovieDetails> getMovieDetails(int movieId) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movie_id}")
                        .build(movieId))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                .retrieve()
                .bodyToMono(MovieDetails.class)
                .blockOptional();
    }

    @Override
    public Optional<ArrayList<CastMember>> getMovieCast(Integer movieId) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movie_id}/credits")
                        .build(movieId))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY)
                .retrieve()
                .bodyToMono(MovieCastMemberResponse.class)
                .map(response -> Optional.ofNullable(response.getCast()).orElse(new ArrayList<>()))
                .blockOptional();
    }



    @Setter
    @Getter
    private static class StephenKingMoviesResponse {
        private List<Movie> cast;
        private List<Movie> crew;
        private int id;
    }

    @Setter
    @Getter
    private static class MovieCastMemberResponse {
        private Integer id;
        private ArrayList<CastMember> cast;
    }

}
