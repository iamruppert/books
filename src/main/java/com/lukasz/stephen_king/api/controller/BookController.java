package com.lukasz.stephen_king.api.controller;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.mapper.BookDtoMapper;
import com.lukasz.stephen_king.buisness.BookService;
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
    public ResponseEntity<List<BookDto>> getBooks(
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<BookDto> books = bookService.getAllBooks(sortBy, sortOrder, page, pageSize);
        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(
            @RequestParam String name,
            @RequestParam(defaultValue = "pages") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<BookDto> books = bookService.findBooks(name, sortBy, sortOrder, page, pageSize);
        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
        BookDto book = bookService.getBook(id);
        return ResponseEntity.ok().body(book);
    }
}
