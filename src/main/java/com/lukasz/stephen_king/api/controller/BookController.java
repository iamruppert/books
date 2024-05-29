package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.buisness.BookService;
import com.lukasz.stephen_king.domain.Book;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getFilteredBooks(
            @PathParam("name") String name
    ){

        List<Book> books = bookService.findBooks(name);
        return ResponseEntity.ok().body(books);
    }
}
