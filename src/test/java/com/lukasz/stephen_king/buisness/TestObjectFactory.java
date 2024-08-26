package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.infrastructure.book.Book;

import java.util.List;

public abstract class TestObjectFactory {

    public static Book createBook1 = Book.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .referenceList(List.of())
            .build();


    public static Book createBook2 = Book.builder()
            .bookId(2)
            .year(1977)
            .title("The Shining")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(447)
            .referenceList(List.of())
            .build();


    public static BookDomain createBookDomain1 = BookDomain.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();


    public static BookDomain createBookDomain2 = BookDomain.builder()
            .bookId(2)
            .year(1977)
            .title("The Shining")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(447)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();


    public static BookDto createBookDto1 = BookDto.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();


    public static BookDto createBookDto2 = BookDto.builder()
            .bookId(2)
            .year(1977)
            .title("The Shining")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(447)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();
}

