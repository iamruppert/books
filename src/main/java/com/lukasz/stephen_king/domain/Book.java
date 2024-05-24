package com.lukasz.stephen_king.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "bookId")
@ToString(of = {
        "bookId", "year", "title",
        "publisher", "ISBN"
})
public class Book {

    String bookId;
    String year;
    String title;
    String publisher;
    String ISBN;
}
