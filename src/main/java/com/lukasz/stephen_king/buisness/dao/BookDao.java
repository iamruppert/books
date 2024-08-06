package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.stephen_king.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookDao {

    List<Book> getBooks(int page, int pageSize);

    List<Book> getAllBooks();

    Optional<Book> getBook(Integer id);
}
