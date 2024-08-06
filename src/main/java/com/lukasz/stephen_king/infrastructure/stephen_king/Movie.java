package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "originalTitle"})
public class Movie {

    int id;
    String originalTitle;

    @JsonCreator
    public Movie(@JsonProperty("id") int id, @JsonProperty("original_title") String originalTitle) {
        this.id = id;
        this.originalTitle = originalTitle;
    }

}
