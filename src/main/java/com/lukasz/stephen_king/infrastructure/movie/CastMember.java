package com.lukasz.stephen_king.infrastructure.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "castMemberId")
@ToString(of = {
        "castMemberId",
        "name",
        "character"
})
public class CastMember {
    Integer castMemberId;
    String name;
    String character;

    @JsonCreator
    public CastMember(@JsonProperty("id") Integer castMemberId,
                @JsonProperty("name") String name,
                @JsonProperty("character") String character
    ) {
        this.castMemberId = castMemberId;
        this.name = name;
        this.character = character;
    }
}
