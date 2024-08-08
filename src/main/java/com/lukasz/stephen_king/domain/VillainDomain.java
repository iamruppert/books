package com.lukasz.stephen_king.domain;

import lombok.*;

@With
@Data
@Builder
@EqualsAndHashCode(of = "villainId")
@ToString(of = {
        "villainId", "name", "gender",
        "status"
})
public class VillainDomain {
    Integer villainId;
    String name;
    String gender;
    String status;
}
