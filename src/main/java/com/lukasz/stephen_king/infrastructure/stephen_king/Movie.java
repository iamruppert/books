package com.lukasz.stephen_king.infrastructure.stephen_king;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of={"id","originalTitle"})
public class Movie {

    int id;
    String originalTitle;
}
