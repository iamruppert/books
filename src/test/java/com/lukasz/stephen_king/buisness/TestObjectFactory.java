package com.lukasz.stephen_king.buisness;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.api.dto.MovieDetailsDto;
import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.domain.*;
import com.lukasz.stephen_king.infrastructure.book.Book;
import com.lukasz.stephen_king.infrastructure.book.Villain;
import com.lukasz.stephen_king.infrastructure.book.VillainReference;
import com.lukasz.stephen_king.infrastructure.movie.CastMember;
import com.lukasz.stephen_king.infrastructure.movie.Movie;
import com.lukasz.stephen_king.infrastructure.movie.MovieDetails;

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

    public static MovieDetails movieDetails1 = MovieDetails.builder()
            .id(694)
            .imdbId("tt0081505")
            .backdropPath("/mmd1HnuvAzFc4iuVJcnBrhDNEKr.jpg")
            .budget(19000000)
            .originalLanguage("en")
            .originalTitle("The Shining")
            .overview("Jack Torrance accepts a caretaker job at the Overlook Hotel, where he, along with his wife Wendy and their son Danny, must live isolated from the rest of the world for the winter. But they aren't prepared for the madness that lurks within.")
            .posterPath("/xazWoLealQwEgqZ89MLZklLZD3k.jpg")
            .releaseDate("1980-05-23")
            .runtime(144)
            .voteAverage(8.214)
            .build();

    public static CastMember castMember1 = CastMember.builder()
            .castMemberId(514)
            .name("Jack Nicholson")
            .character("Jack Torrance")
            .build();

    public static CastMember castMember2 = CastMember.builder()
            .castMemberId(10409)
            .name("Shelley Duvall")
            .character("Wendy Torrance")
            .build();

    public static CastMemberDomain castMemberDomain1 = CastMemberDomain.builder()
            .castMemberId(514)
            .name("Jack Nicholson")
            .character("Jack Torrance")
            .build();

    public static CastMemberDomain castMemberDomain2 = CastMemberDomain.builder()
            .castMemberId(10409)
            .name("Shelley Duvall")
            .character("Wendy Torrance")
            .build();

    public static MovieDetailsDomain movieDetailsDomain1 = MovieDetailsDomain.builder()
            .id(694)
            .imdbId("tt0081505")
            .backdropPath("/mmd1HnuvAzFc4iuVJcnBrhDNEKr.jpg")
            .budget(19000000)
            .originalLanguage("en")
            .originalTitle("The Shining")
            .overview("Jack Torrance accepts a caretaker job at the Overlook Hotel, where he, along with his wife Wendy and their son Danny, must live isolated from the rest of the world for the winter. But they aren't prepared for the madness that lurks within.")
            .posterPath("/xazWoLealQwEgqZ89MLZklLZD3k.jpg")
            .releaseDate("1980-05-23")
            .runtime(144)
            .voteAverage(8.214)
            .cast(new ArrayList<>(List.of(castMemberDomain1, castMemberDomain2)))
            .build();



    public static MovieDetailsDto movieDetailsDto1 = MovieDetailsDto.builder()
            .id(694)
            .imdbId("tt0081505")
            .backdropPath("/mmd1HnuvAzFc4iuVJcnBrhDNEKr.jpg")
            .budget(19000000)
            .originalLanguage("en")
            .originalTitle("The Shining")
            .overview("Jack Torrance accepts a caretaker job at the Overlook Hotel, where he, along with his wife Wendy and their son Danny, must live isolated from the rest of the world for the winter. But they aren't prepared for the madness that lurks within.")
            .posterPath("/xazWoLealQwEgqZ89MLZklLZD3k.jpg")
            .releaseDate("1980-05-23")
            .runtime(144)
            .voteAverage(8.214)
            .cast(new ArrayList<>(List.of(castMemberDomain1, castMemberDomain2)))
            .build();

}

