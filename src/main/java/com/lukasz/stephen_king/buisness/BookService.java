package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.domain.exception.NotFoundException;
import com.lukasz.stephen_king.buisness.mapper.BookMapper;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.infrastructure.stephen_king.Book;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    public BookDomain getBook(Integer id) {
        Optional<Book> optionalBook = bookDao.getBook(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return this.fetchAndMapDescription(List.of(book)).getFirst();
        }
        else{
            throw new NotFoundException("Cannot find book with id: [%s]".formatted(id));
        }
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
                .peek(b -> {
                    String fileNameBase = b.getTitle().toLowerCase()
                            .replace(":", "")
                            .replace(".", "")
                            .replace("'", "-")
                            .replace("/", "-")
                            .replace(" ", "-");

                    String descriptionFileName = fileNameBase + ".txt";
                    String imageFileName = fileNameBase + ".jpeg";
                    String description;
                    Blob image;
                    try {
                        description = readDescriptionFromFile(descriptionFileName);
                        image = readImageFromFile(imageFileName);
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                    b.setDescription(description);
                    b.setImage(image);
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

    private Blob readImageFromFile(String fileName) throws IOException, SQLException {
        ClassPathResource resource = new ClassPathResource("book-covers/" + fileName);
        if (resource.exists()) {
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
            return new SerialBlob(imageBytes);
        }
        return null;
    }


}
