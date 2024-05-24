package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.buisness.BookService;
import com.lukasz.stephen_king.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

}
