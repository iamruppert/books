package com.lukasz.stephen_king.infrastructure.stephen_king;

import com.lukasz.stephen_king.buisness.dao.BookDao;
import com.lukasz.stephen_king.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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

        ApiResponse response = responseMono.block(); // Blocking for simplicity
        return response.getData();
    }

    @Setter
    @Getter
    private static class ApiResponse {
        private List<Book> data;
    }
}
