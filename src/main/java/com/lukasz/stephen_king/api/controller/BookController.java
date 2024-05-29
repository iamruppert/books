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
    public ResponseEntity<?> getAllBooks(
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Book> books = bookService.getAllBooks();

        books = bookService.sortBooks(books, sortBy, sortOrder);

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getFilteredBooks(
            @PathParam("name") String name,
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){

        List<Book> books = bookService.findBooks(name);
        books = bookService.sortBooks(books, sortBy, sortOrder);
        return ResponseEntity.ok().body(books);
    }
}
