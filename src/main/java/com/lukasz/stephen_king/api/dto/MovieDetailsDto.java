package com.lukasz.stephen_king.api.dto;

import com.lukasz.stephen_king.domain.CastMemberDomain;
import lombok.*;

import java.util.ArrayList;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsDto {

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
