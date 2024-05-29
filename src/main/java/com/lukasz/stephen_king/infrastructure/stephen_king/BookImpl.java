package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookImpl implements BookDao {

    private final WebClient webClient;

    @Override
    public List<Book> getAllBooks() {
        Mono<ApiResponse> responseMono = webClient.get()
                .uri("/api/books")
                .retrieve()
                .bodyToMono(ApiResponse.class);

        ApiResponse response = responseMono.block();
        return response.getData();
    }

    @Override
    public Optional<Book> getBook(Integer id) {
        try {
            Mono<BookResponse> responseMono = webClient.get()
                    .uri("/api/book/" + id)
                    .retrieve()
                    .bodyToMono(BookResponse.class);

            BookResponse response = responseMono.block();
            return Optional.ofNullable(response.getData());
        } catch (Exception e) {
            return Optional.empty();
        }
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
    }}
