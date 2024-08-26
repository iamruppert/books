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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookDtoMapper bookDtoMapper;

    @Mock
    private VillainMapper villainMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    public void shouldReturnSortedBooksByTitleCorrectly() {

        Book book1 = TestObjectFactory.createBook1;
        Book book2 = TestObjectFactory.createBook2;

        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDomain bookDomain2 = TestObjectFactory.createBookDomain2;

        BookDto bookDto1 = TestObjectFactory.createBookDto1;
        BookDto bookDto2 = TestObjectFactory.createBookDto2;

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

    @Test
    public void shouldReturnSortedBooksByYearCorrectly() {
        Book book1 = TestObjectFactory.createBook1;
        Book book2 = TestObjectFactory.createBook2;

        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDomain bookDomain2 = TestObjectFactory.createBookDomain2;

        BookDto bookDto1 = TestObjectFactory.createBookDto1;
        BookDto bookDto2 = TestObjectFactory.createBookDto2;

        when(bookDao.getAllBooks()).thenReturn(List.of(book1, book2));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookMapper.map(book2)).thenReturn(bookDomain2);
        when(bookDtoMapper.mapToDto(bookDomain1)).thenReturn(bookDto1);
        when(bookDtoMapper.mapToDto(bookDomain2)).thenReturn(bookDto2);

        List<BookDto> result = bookService.getAllBooks("year", "asc", 0, 10);

        assertEquals(2, result.size());
        assertEquals(bookDto2, result.get(0));
        assertEquals(bookDto1, result.get(1));

        result = bookService.getAllBooks("year", "asc", 0, 1);

        assertEquals(1, result.size());
        assertEquals(bookDto2, result.get(0));
    }

    @Test
    public void shouldReturnSortedBooksByPagesCorrectly() {
        Book book1 = TestObjectFactory.createBook1;
        Book book2 = TestObjectFactory.createBook2;

        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDomain bookDomain2 = TestObjectFactory.createBookDomain2;

        BookDto bookDto1 = TestObjectFactory.createBookDto1;
        BookDto bookDto2 = TestObjectFactory.createBookDto2;

        when(bookDao.getAllBooks()).thenReturn(List.of(book1, book2));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookMapper.map(book2)).thenReturn(bookDomain2);
        when(bookDtoMapper.mapToDto(bookDomain1)).thenReturn(bookDto1);
        when(bookDtoMapper.mapToDto(bookDomain2)).thenReturn(bookDto2);

        List<BookDto> result = bookService.getAllBooks("pages", "asc", 0, 10);

        assertEquals(2, result.size());
        assertEquals(bookDto2, result.get(0));
        assertEquals(bookDto1, result.get(1));

        result = bookService.getAllBooks("pages", "asc", 0, 1);

        assertEquals(1, result.size());
        assertEquals(bookDto2, result.get(0));
    }

    @Test
    public void shouldReturnBooksByNameCorrectly() {
        Book book1 = TestObjectFactory.createBook1;
        Book book2 = TestObjectFactory.createBook2;
        Book book3 = TestObjectFactory.createBook3;
        Book book4 = TestObjectFactory.createBook4;

        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDomain bookDomain2 = TestObjectFactory.createBookDomain2;
        BookDomain bookDomain3 = TestObjectFactory.createBookDomain3;
        BookDomain bookDomain4 = TestObjectFactory.createBookDomain4;

        BookDto bookDto3 = TestObjectFactory.createBookDto3;

        when(bookDao.getAllBooks()).thenReturn(List.of(book1, book2, book3, book4));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookMapper.map(book2)).thenReturn(bookDomain2);
        when(bookMapper.map(book3)).thenReturn(bookDomain3);
        when(bookMapper.map(book4)).thenReturn(bookDomain4);
        when(bookDtoMapper.mapToDto(bookDomain3)).thenReturn(bookDto3);

        List<BookDto> result = bookService.findBooks("sa", "title", "asc", 0, 10);

        assertEquals(1, result.size());
        assertEquals(bookDto3, result.get(0));
    }

    @Test
    public void shouldReturnAllBooksAndSearchByNameCorrectly() {

        Book book1 = TestObjectFactory.createBook1;
        Book book2 = TestObjectFactory.createBook2;
        Book book3 = TestObjectFactory.createBook3;
        Book book4 = TestObjectFactory.createBook4;
        Book book5 = TestObjectFactory.createBook5;
        Book book6 = TestObjectFactory.createBook6;

        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDomain bookDomain2 = TestObjectFactory.createBookDomain2;
        BookDomain bookDomain3 = TestObjectFactory.createBookDomain3;
        BookDomain bookDomain4 = TestObjectFactory.createBookDomain4;
        BookDomain bookDomain5 = TestObjectFactory.createBookDomain5;
        BookDomain bookDomain6 = TestObjectFactory.createBookDomain6;

        BookDto bookDto1 = TestObjectFactory.createBookDto1;
        BookDto bookDto2 = TestObjectFactory.createBookDto2;
        BookDto bookDto3 = TestObjectFactory.createBookDto3;
        BookDto bookDto4 = TestObjectFactory.createBookDto4;
        BookDto bookDto5 = TestObjectFactory.createBookDto5;
        BookDto bookDto6 = TestObjectFactory.createBookDto6;

        when(bookDao.getAllBooks()).thenReturn(List.of(book1, book2, book3, book4, book5, book6));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookMapper.map(book2)).thenReturn(bookDomain2);
        when(bookMapper.map(book3)).thenReturn(bookDomain3);
        when(bookMapper.map(book4)).thenReturn(bookDomain4);
        when(bookMapper.map(book5)).thenReturn(bookDomain5);
        when(bookMapper.map(book6)).thenReturn(bookDomain6);
        when(bookDtoMapper.mapToDto(bookDomain1)).thenReturn(bookDto1);
        when(bookDtoMapper.mapToDto(bookDomain2)).thenReturn(bookDto2);
        when(bookDtoMapper.mapToDto(bookDomain3)).thenReturn(bookDto3);
        when(bookDtoMapper.mapToDto(bookDomain4)).thenReturn(bookDto4);
        when(bookDtoMapper.mapToDto(bookDomain5)).thenReturn(bookDto5);
        when(bookDtoMapper.mapToDto(bookDomain6)).thenReturn(bookDto6);

        List<BookDto> allBooksResult = bookService.getAllBooks("title", "asc", 0, 10);
        assertEquals(6, allBooksResult.size());
        assertTrue(allBooksResult.containsAll(List.of(bookDto1, bookDto2, bookDto3, bookDto4, bookDto5, bookDto6)));

        List<BookDto> searchResult = bookService.findBooks("tower", "title", "asc", 0, 10);
        assertEquals(2, searchResult.size());
        assertTrue(searchResult.containsAll(List.of(bookDto5, bookDto6)));
    }

    @Test
    public void shouldReturnBookByIdCorrectly() {
        Book book1 = TestObjectFactory.createBook1;
        BookDomain bookDomain1 = TestObjectFactory.createBookDomain1;
        BookDto bookDto1 = TestObjectFactory.createBookDto1;
        Villain villain1 = TestObjectFactory.createVillain1;
        VillainDomain villainDomain1 = TestObjectFactory.createVillainDomain1;

        when(bookDao.getBook(1)).thenReturn(Optional.of(book1));
        when(bookMapper.map(book1)).thenReturn(bookDomain1);
        when(bookDtoMapper.mapToDto(bookDomain1)).thenReturn(bookDto1);
        when(bookDao.getVillain(1)).thenReturn(villain1);
        when(villainMapper.mapToDomain(villain1)).thenReturn(villainDomain1);

        BookDto result = bookService.getBook(1);

        assertEquals(bookDto1, result);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenBookNotFound() {
        int bookId = 999;

        when(bookDao.getBook(bookId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.getBook(bookId);
        });

        assertEquals("Cannot find book with id: [999]", exception.getMessage());
    }

}