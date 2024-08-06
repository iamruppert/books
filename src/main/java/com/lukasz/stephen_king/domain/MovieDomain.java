package com.lukasz.stephen_king.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "originalTitle"})
public class MovieDomain {

    int id;
    String originalTitle;

}
