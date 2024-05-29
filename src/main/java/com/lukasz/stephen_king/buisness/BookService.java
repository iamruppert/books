package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public List<Book> findBooks(String name) {
        List<Book> allBooks = bookDao.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> sortBooks(List<Book> books, String sortBy, String sortOrder) {
        Comparator<Book> comparator = switch (sortBy) {
            case "pages" -> Comparator.comparingInt(Book::getPages);
            case "year" -> Comparator.comparingInt(Book::getYear);
            case "title" -> Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
            default -> null;
        };

        if (comparator != null) {
            if (sortOrder.equalsIgnoreCase(Sort.Direction.DESC.name())) {
                comparator = comparator.reversed();
            }
            books = books.stream().sorted(comparator).collect(Collectors.toList());
        }

        return books;
    }
}
