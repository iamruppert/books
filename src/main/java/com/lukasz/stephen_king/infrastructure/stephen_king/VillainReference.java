package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "villainId")
@ToString(of = {
        "villainId",
        "name", "url"
})
public class VillainReference {

    Integer villainId;
    String name;
    String url;

    @JsonCreator
    public VillainReference(@JsonProperty("id") Integer villainId,
                            @JsonProperty("name") String name,
                            @JsonProperty("url") String url) {
        this.villainId = villainId;
        this.name = name;
        this.url = url;
    }
}
