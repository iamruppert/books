package com.lukasz.stephen_king.domain;

import lombok.*;

import java.sql.Blob;

@With
@Data
@Builder
@EqualsAndHashCode(of = "bookId")
@ToString(of = {
        "bookId", "year", "title",
        "publisher", "ISBN", "pages", "description","image"
})
public class BookDomain {

    String bookId;
    Integer year;
    String title;
    String publisher;
    String ISBN;
    Integer pages;
    String description;
    Blob image;
}
