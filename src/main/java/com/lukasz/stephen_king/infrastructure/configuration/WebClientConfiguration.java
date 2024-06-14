package com.lukasz.stephen_king.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

    public static final String STEPHEN_KING_URL = "https://stephen-king-api.onrender.com/";
    public static final String TMDB_URL = "https://api.themoviedb.org/3/";
    public static final int TIMEOUT = 5000;

    private HttpClient httpClient(){
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .responseTimeout(Duration.ofMillis(TIMEOUT))
                .doOnConnected(conn->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)));
    }

    private ExchangeStrategies exchangeStrategies(final ObjectMapper objectMapper){
        return ExchangeStrategies
                .builder()
                .codecs(configurer -> {
                    configurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                    configurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                }).build();
    }

    @Bean(name = "stephenKingWebClient")
    public WebClient stephenKingWebClient(final ObjectMapper objectMapper){
        return WebClient.builder()
                .baseUrl(STEPHEN_KING_URL)
                .exchangeStrategies(exchangeStrategies(objectMapper))
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .build();
    }

    @Bean(name = "tmdbWebClient")
    public WebClient tmbdWebClient(final ObjectMapper objectMapper){

        return WebClient.builder()
                .baseUrl(TMDB_URL)
                .exchangeStrategies(exchangeStrategies(objectMapper))
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .build();
    }
}
