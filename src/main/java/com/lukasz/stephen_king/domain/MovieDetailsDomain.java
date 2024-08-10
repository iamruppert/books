package com.lukasz.stephen_king.domain;

import com.lukasz.stephen_king.infrastructure.stephen_king.CastMember;
import lombok.*;

import java.util.ArrayList;

@With
@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {
        "id",
        "imdbId", "backdropPath", "budget", "originalLanguage",
        "originalTitle", "overview", "posterPath", "releaseDate", "runtime", "voteAverage", "cast"
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

    ArrayList<CastMemberDomain> cast;

}
