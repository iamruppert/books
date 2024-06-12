package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.mapper.BookDtoMapper;
import com.lukasz.stephen_king.buisness.BookService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;
    private final BookDtoMapper bookDtoMapper;

    @CrossOrigin(origins = "*")
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<BookDto> books = bookService.getAllBooks(sortBy, sortOrder);
        return ResponseEntity.ok().body(books);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> getFilteredBooks(
            @PathParam("name") String name,
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<BookDto> books = bookService.findBooks(name, sortBy, sortOrder);
        return ResponseEntity.ok().body(books);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
        BookDto book = bookService.getBook(id);
        return ResponseEntity.ok().body(book);
    }
}
