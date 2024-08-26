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

}