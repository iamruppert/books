package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.stephen_king.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDao {

    List<Book> getAllBooks();
}
