package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@With
@Value
@Builder
@EqualsAndHashCode(of = "villainId")
@ToString(of = {
        "villainId", "name", "gender",
        "status", "notes"
})
public class Villain {
    Integer villainId;
    String name;
    String gender;
    String status;
    ArrayList<String> notes;

    @JsonCreator
    public Villain(@JsonProperty("id") Integer villainId,
                   @JsonProperty("name") String name,
                   @JsonProperty("gender") String gender,
                   @JsonProperty("status") String status,
                   @JsonProperty("notes") ArrayList<String> notes
                   ){
        this.villainId = villainId;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.notes = notes;
    }
}
