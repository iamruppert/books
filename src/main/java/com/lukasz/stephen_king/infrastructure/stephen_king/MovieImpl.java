package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.lukasz.stephen_king.buisness.dao.MovieDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                .bodyToMono(MovieCreditsResponse.class)
                .map(MovieCreditsResponse::getCrew)
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

    @Setter
    @Getter
    private static class MovieCreditsResponse {
        private List<Movie> cast;
        private List<Movie> crew;
        private int id;
    }

}
