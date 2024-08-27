package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.domain.BookDomain;
import com.lukasz.stephen_king.domain.MovieDomain;
import com.lukasz.stephen_king.domain.VillainDomain;
import com.lukasz.stephen_king.infrastructure.book.Book;
import com.lukasz.stephen_king.infrastructure.book.Villain;
import com.lukasz.stephen_king.infrastructure.book.VillainReference;
import com.lukasz.stephen_king.infrastructure.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class TestObjectFactory {

    public static VillainReference createVillainReference1 = VillainReference.builder()
            .villainId(1)
            .name("Pennywise")
            .url("http://localhost:8080/villains/1")
            .build();

    public static VillainDomain createVillainDomain1 = VillainDomain.builder()
            .villainId(1)
            .name("Pennywise")
            .gender("Unknown")
            .status("Active")
            .build();

    public static Villain createVillain1 = Villain.builder()
            .villainId(1)
            .name("Pennywise")
            .gender("Unknown")
            .status("Active")
            .notes(new ArrayList<>(List.of("Shape-shifter", "Preys on children")))
            .build();

    public static Book createBook1 = Book.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .referenceList(List.of(createVillainReference1))
            .build();


    public static Book createBook2 = Book.builder()
            .bookId(2)
            .year(1977)
            .title("The Shining")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(447)
            .referenceList(List.of())
            .build();


    public static BookDomain createBookDomain1 = BookDomain.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of(createVillainDomain1))
            .build();


    public static BookDomain createBookDomain2 = BookDomain.builder()
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


    public static BookDto createBookDto1 = BookDto.builder()
            .bookId(1)
            .year(1986)
            .title("It")
            .publisher("Viking")
            .ISBN("978-0450411434")
            .pages(1138)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of(createVillain1))
            .build();


    public static BookDto createBookDto2 = BookDto.builder()
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

    public static Book createBook3 = Book.builder()
            .bookId(3)
            .year(1975)
            .title("Salem's Lot")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(439)
            .referenceList(List.of())
            .build();

    public static BookDomain createBookDomain3 = BookDomain.builder()
            .bookId(3)
            .year(1975)
            .title("Salem's Lot")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(439)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();

    public static BookDto createBookDto3 = BookDto.builder()
            .bookId(3)
            .year(1975)
            .title("Salem's Lot")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(439)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();

    public static Book createBook4 = Book.builder()
            .bookId(4)
            .year(1983)
            .title("Pet Sematary")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(374)
            .referenceList(List.of())
            .build();

    public static BookDomain createBookDomain4 = BookDomain.builder()
            .bookId(4)
            .year(1983)
            .title("Pet Sematary")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(374)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();

    public static BookDto createBookDto4 = BookDto.builder()
            .bookId(4)
            .year(1983)
            .title("Pet Sematary")
            .publisher("Doubleday")
            .ISBN("978-0385121675")
            .pages(374)
            .description("A horror novel by Stephen King.")
            .image(null)
            .villains(List.of())
            .build();

    public static Book createBook5 = Book.builder()
            .bookId(5)
            .year(1982)
            .title("The Dark Tower: The Gunslinger")
            .publisher("Grant")
            .ISBN("978-0937986509")
            .pages(224)
            .referenceList(List.of())
            .build();

    public static Book createBook6 = Book.builder()
            .bookId(6)
            .year(1987)
            .title("The Dark Tower II: The Drawing of the Three")
            .publisher("Grant")
            .ISBN("978-0937986523")
            .pages(400)
            .referenceList(List.of())
            .build();

    public static BookDomain createBookDomain5 = BookDomain.builder()
            .bookId(5)
            .year(1982)
            .title("The Dark Tower: The Gunslinger")
            .publisher("Grant")
            .ISBN("978-0937986509")
            .pages(224)
            .description("The first novel in Stephen King's The Dark Tower series.")
            .image(null)
            .villains(List.of())
            .build();

    public static BookDomain createBookDomain6 = BookDomain.builder()
            .bookId(6)
            .year(1987)
            .title("The Dark Tower II: The Drawing of the Three")
            .publisher("Grant")
            .ISBN("978-0937986523")
            .pages(400)
            .description("The second novel in Stephen King's The Dark Tower series.")
            .image(null)
            .villains(List.of())
            .build();

    public static BookDto createBookDto5 = BookDto.builder()
            .bookId(5)
            .year(1982)
            .title("The Dark Tower: The Gunslinger")
            .publisher("Grant")
            .ISBN("978-0937986509")
            .pages(224)
            .description("The first novel in Stephen King's The Dark Tower series.")
            .image(null)
            .villains(List.of())
            .build();

    public static BookDto createBookDto6 = BookDto.builder()
            .bookId(6)
            .year(1987)
            .title("The Dark Tower II: The Drawing of the Three")
            .publisher("Grant")
            .ISBN("978-0937986523")
            .pages(400)
            .description("The second novel in Stephen King's The Dark Tower series.")
            .image(null)
            .villains(List.of())
            .build();

    public static Movie createMovie1 = Movie.builder()
            .id(1)
            .originalTitle("The Shining")
            .build();

    public static Movie createMovie2 = Movie.builder()
            .id(2)
            .originalTitle("It")
            .build();

    public static MovieDomain createMovieDomain1 = MovieDomain.builder()
            .id(1)
            .originalTitle("The Shining")
            .build();

    public static MovieDomain createMovieDomain2 = MovieDomain.builder()
            .id(2)
            .originalTitle("It")
            .build();

    public static MovieDto createMovieDto1 = MovieDto.builder()
            .id(1)
            .originalTitle("The Shining")
            .build();

    public static MovieDto createMovieDto2 = MovieDto.builder()
            .id(2)
            .originalTitle("It")
            .build();

}

