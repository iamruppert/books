package com.lukasz.stephen_king.api.dto;

import lombok.*;

import java.sql.Blob;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    Integer bookId;
    Integer year;
    String title;
    String publisher;
    String ISBN;
    Integer pages;
    String description;
    Blob image;

}
