package com.lukasz.stephen_king.domain;

import lombok.*;

@With
@Setter
@Data
@Builder
@EqualsAndHashCode(of = "castMemberId")
@ToString(of = {
        "castMemberId", "name", "character"
})
public class CastMemberDomain {
    Integer castMemberId;
    String name;
    String character;
}
