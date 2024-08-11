package com.lukasz.stephen_king.infrastructure.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {
        "id",
        "imdbId", "backdropPath", "budget", "originalLanguage",
        "originalTitle", "overview", "posterPath", "releaseDate", "runtime", "voteAverage"
})
public class MovieDetails {

    int id;
    String imdbId;
    String backdropPath;
    int budget;
    String originalLanguage;
    String originalTitle;
    String overview;
    String posterPath;
    String releaseDate;
    int runtime;
    double voteAverage;

    @JsonCreator
    public MovieDetails(
            @JsonProperty("id") int id,
            @JsonProperty("imdb_id") String imdbId,
            @JsonProperty("backdrop_path") String backdropPath,
            @JsonProperty("budget") int budget,
            @JsonProperty("original_language") String originalLanguage,
            @JsonProperty("original_title") String originalTitle,
            @JsonProperty("overview") String overview,
            @JsonProperty("poster_path") String posterPath,
            @JsonProperty("release_date") String releaseDate,
            @JsonProperty("runtime") int runtime,
            @JsonProperty("vote_average") double voteAverage

    ) {
        this.id = id;
        this.imdbId = imdbId;
        this.backdropPath = backdropPath;
        this.budget = budget;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
    }

}
