package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }
}
