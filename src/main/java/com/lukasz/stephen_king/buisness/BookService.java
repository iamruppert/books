package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.mapper.BookDtoMapper;
import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.buisness.mapper.BookMapper;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.domain.exception.NotFoundException;
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
    private final BookDtoMapper bookDtoMapper;

    public List<BookDto> getAllBooks(String sortBy, String sortOrder, int page, int pageSize) {
        List<Book> books = bookDao.getBooks(page, pageSize);
        List<BookDomain> list = books.stream()
                .map(bookMapper::map)
                .toList();
        List<BookDomain> bookDomains = sortBooks(list, sortBy, sortOrder);
        return addMorePropertiesToBookAndMapToDto(bookDomains);
    }

    public BookDto getBook(Integer id) {
        Optional<Book> optionalBook = bookDao.getBook(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDomain mappedBook = bookMapper.map(book);
            return this.addMorePropertiesToBookAndMapToDto(List.of(mappedBook)).getFirst();
        } else {
            throw new NotFoundException("Cannot find book with id: [%s]".formatted(id));
        }
    }

    public List<BookDto> findBooks(String name, String sortBy, String sortOrder) {
        List<Book> allBooks = bookDao.getAllBooks();
        List<BookDomain> list = allBooks.stream()
                .map(bookMapper::map)
                .toList();
        List<BookDomain> filteredBooks = list.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        List<BookDomain> bookDomains = sortBooks(filteredBooks, sortBy, sortOrder);
        return addMorePropertiesToBookAndMapToDto(bookDomains);
    }

    private List<BookDomain> sortBooks(List<BookDomain> books, String sortBy, String sortOrder) {
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


    private List<BookDto> addMorePropertiesToBookAndMapToDto(List<BookDomain> books) {
        return books.stream()
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
                .map(bookDtoMapper::mapToDto)
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
