package com.lukasz.stephen_king.api.dto;

import com.lukasz.stephen_king.infrastructure.book.Villain;
import lombok.*;

import java.sql.Blob;
import java.util.List;

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
    List<Villain> villains;
}
