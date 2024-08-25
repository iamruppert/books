package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.mapper.BookDtoMapper;
import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.buisness.mapper.BookMapper;
import com.lukasz.stephen_king.buisness.mapper.VillainMapper;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.domain.VillainDomain;
import com.lukasz.stephen_king.domain.exception.NotFoundException;
import com.lukasz.stephen_king.infrastructure.book.Book;
import com.lukasz.stephen_king.infrastructure.book.Villain;
import com.lukasz.stephen_king.infrastructure.book.VillainReference;
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
import java.util.ArrayList;
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

    private final VillainMapper villainMapper;
    

    public List<BookDto> getAllBooks(String sortBy, String sortOrder, int page, int pageSize) {
        List<Book> books = bookDao.getAllBooks();
        List<BookDomain> list = books.stream()
                .map(bookMapper::map)
                .toList();
        List<BookDomain> sortedBooks = sortBooks(list, sortBy, sortOrder);
        List<BookDomain> paginatedBooks = paginate(sortedBooks, page, pageSize);
        return addPropertiesAndMapToDto(paginatedBooks);
    }

    public BookDto getBook(Integer id) {
        Optional<Book> optionalBook = bookDao.getBook(id);
        List<VillainDomain> villainDomains = new ArrayList<>();
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDomain bookDomain = bookMapper.map(book);
            List<VillainReference> referenceList = book.getReferenceList();
            for (VillainReference villainReference : referenceList) {
                String url = villainReference.getUrl();
                Integer villainId = Integer.valueOf(url.substring(url.lastIndexOf("/") + 1));
                Villain villain = bookDao.getVillain(villainId);
                VillainDomain villainDomain = villainMapper.mapToDomain(villain);
                villainDomains.add(villainDomain);
            }
            bookDomain.setVillains(villainDomains);
            return addPropertiesAndMapToDto(List.of(bookDomain)).getFirst();
        } else {
            throw new NotFoundException("Cannot find book with id: [%s]".formatted(id));
        }
    }

    public List<BookDto> findBooks(String name, String sortBy, String sortOrder, int page, int pageSize) {
        List<Book> allBooks = bookDao.getAllBooks();
        List<BookDomain> bookDomains = allBooks.stream()
                .map(bookMapper::map)
                .filter(book -> book.getTitle().toLowerCase().contains(name.toLowerCase()))
                .toList();

        List<BookDomain> sortedBooks = sortBooks(bookDomains, sortBy, sortOrder);
        List<BookDomain> paginatedBooks = paginate(sortedBooks, page, pageSize);
        return addPropertiesAndMapToDto(paginatedBooks);
    }

    private List<BookDomain> paginate(List<BookDomain> books, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, books.size());
        return books.subList(start, end);
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

    private List<BookDto> addPropertiesAndMapToDto(List<BookDomain> books) {
        return books.stream()
                .map(book -> {
                    String fileNameBase = book.getTitle().toLowerCase()
                            .replace(":", "")
                            .replace(".", "")
                            .replace("'", "-")
                            .replace("/", "-")
                            .replace(" ", "-");

                    String descriptionFileName = fileNameBase + ".txt";
                    String imageFileName = fileNameBase + ".jpeg";

                    try {
                        book.setDescription(readDescriptionFromFile(descriptionFileName));
                        book.setImage(readImageFromFile(imageFileName));
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return bookDtoMapper.mapToDto(book);
                })
                .toList();
    }

    private String readDescriptionFromFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("book-descriptions/" + fileName);
        if (resource.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                return reader.lines().collect(Collectors.joining("\n"));
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
