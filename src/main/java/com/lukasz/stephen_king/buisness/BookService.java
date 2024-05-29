package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.buisness.mapper.BookMapper;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.infrastructure.stephen_king.Book;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;

    public List<BookDomain> getAllBooks() {
        List<Book> allBooks = bookDao.getAllBooks();
        return fetchAndMapDescription(allBooks);
    }

    public List<BookDomain> findBooks(String name) {
        List<Book> allBooks = bookDao.getAllBooks();
        List<Book> filteredBooks = allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return fetchAndMapDescription(filteredBooks);
    }

    public List<BookDomain> sortBooks(List<BookDomain> books, String sortBy, String sortOrder) {
        Comparator<BookDomain> comparator = switch (sortBy) {
            case "pages" -> Comparator.comparingInt(BookDomain::getPages);
            case "year" -> Comparator.comparingInt(BookDomain::getYear);
            case "title" -> Comparator.comparing(BookDomain::getTitle, String.CASE_INSENSITIVE_ORDER);
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


    private List<BookDomain> fetchAndMapDescription(List<Book> books) {

        return books.stream()
                .map(bookMapper::map)
                .peek(b->{
                    String fileName = b.getTitle().toLowerCase().replace(" ", "-") + ".txt";
                    String description;
                    try {
                        description = readDescriptionFromFile(fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    b.setDescription(description);

                })
                .collect(Collectors.toList());
    }

    private String readDescriptionFromFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("book-descriptions/" + fileName);
        if (resource.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                StringBuilder descriptionBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    descriptionBuilder.append(line);
                }
                return descriptionBuilder.toString();
            }
        }
        return null;
    }


}
