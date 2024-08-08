package com.lukasz.stephen_king.buisness.dao;

import com.lukasz.stephen_king.infrastructure.stephen_king.Book;
import com.lukasz.stephen_king.infrastructure.stephen_king.Villain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookDao {

    List<Book> getAllBooks();

    Optional<Book> getBook(Integer id);

    Villain getVillain(Integer id);
}
