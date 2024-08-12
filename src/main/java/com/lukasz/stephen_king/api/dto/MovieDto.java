package com.lukasz.stephen_king.api.dto;


import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    int id;
    String originalTitle;

}
