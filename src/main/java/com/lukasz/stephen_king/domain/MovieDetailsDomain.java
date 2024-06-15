package com.lukasz.stephen_king.domain;

import lombok.*;

@With
@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {
        "id",
        "imdbId", "backdropPath", "budget", "originalLanguage",
        "originalTitle", "overview", "posterPath", "releaseDate", "runtime", "voteAverage"
})
public class MovieDetailsDomain {

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


}
