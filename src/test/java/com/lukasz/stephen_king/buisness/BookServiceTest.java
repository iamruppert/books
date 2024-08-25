package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.mapper.BookDtoMapper;
import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.buisness.mapper.BookMapper;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.infrastructure.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookDtoMapper bookDtoMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    public void shouldReturnSortedBooksByTitleCorrectly() {

        Book book1 = Book.builder()
                .bookId(1)
                .year(1986)
                .title("It")
                .publisher("Viking")
                .ISBN("978-0450411434")
                .pages(1138)
                .referenceList(List.of())
                .build();

        Book book2 = Book.builder()
                .bookId(2)
                .year(1977)
                .title("The Shining")
                .publisher("Doubleday")
                .ISBN("978-0385121675")
                .pages(447)
                .referenceList(List.of())
                .build();

        BookDomain bookDomain1 = BookDomain.builder()
                .bookId(1)
                .year(1986)
                .title("It")
                .publisher("Viking")
                .ISBN("978-0450411434")
                .pages(1138)
                .description("A horror novel by Stephen King.")
                .image(null)
                .villains(List.of())
                .build();

        BookDomain bookDomain2 = BookDomain.builder()
                .bookId(2)
                .year(1977)
                .title("The Shining")
                .publisher("Doubleday")
                .ISBN("978-0385121675")
                .pages(447)
                .description("A horror novel by Stephen King.")
                .image(null)
                .villains(List.of())
                .build();

        BookDto bookDto1 = new BookDto(1, 1986, "It", "Viking", "978-0450411434", 1138, "A horror novel by Stephen King.", null, List.of());
        BookDto bookDto2 = new BookDto(2, 1977, "The Shining", "Doubleday", "978-0385121675", 447, "A horror novel by Stephen King.", null, List.of());

        when(bookDao.getAllBooks()).thenReturn(List.of(book1, book2));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookMapper.map(book2)).thenReturn(bookDomain2);
        when(bookDtoMapper.mapToDto(bookDomain1)).thenReturn(bookDto1);
        when(bookDtoMapper.mapToDto(bookDomain2)).thenReturn(bookDto2);

        List<BookDto> result = bookService.getAllBooks("title", "desc", 0, 10);

        assertEquals(2, result.size());
        assertEquals(bookDto2, result.get(0));
        assertEquals(bookDto1, result.get(1));

        result = bookService.getAllBooks("title", "desc", 0, 1);

        assertEquals(1, result.size());
        assertEquals(bookDto2, result.get(0));
    }

}