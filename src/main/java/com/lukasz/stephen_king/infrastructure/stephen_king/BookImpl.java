package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class BookImpl implements BookDao {
    private final WebClient webClient;

    @Autowired
    public BookImpl(@Qualifier("stephenKingWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Book> getAllBooks() {
        return webClient.get()
                .uri("/api/books")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .map(ApiResponse::getData)
                .block();
    }

    @Override
    public Optional<Book> getBook(Integer id) {
        return webClient.get()
                .uri("/api/book/" + id)
                .retrieve()
                .bodyToMono(BookResponse.class)
                .map(BookResponse::getData)
                .blockOptional();
    }

    @Setter
    @Getter
    private static class ApiResponse {
        private List<Book> data;
    }

    @Setter
    @Getter
    private static class BookResponse {
        private Book data;
    }
}
