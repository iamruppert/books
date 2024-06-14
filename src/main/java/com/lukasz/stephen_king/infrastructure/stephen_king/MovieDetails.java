package com.lukasz.stephen_king.infrastructure.stephen_king;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {
        "imdbId", "releaseDate", "originalTitle",
        "budget", "runtime", "voteAverage"
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
    long revenue;
    int runtime;
    double voteAverage;

}
