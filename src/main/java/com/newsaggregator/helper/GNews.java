package com.newsaggregator.helper;

import com.newsaggregator.model.NewsApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class GNews {
    private final String API_KEY="0c8925cff611b20e3b70098fff3b5b74";
    private final String BASE_URL = "https://gnews.io/api/v4";
    @Autowired
    private WebClient webClient;
    public Mono<NewsApiResponse> fetchNews(String query, Set category, String language, String country){
        UriComponentsBuilder uriComponentsBuilder=UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q",query)
                .queryParam("category", category)
                .queryParam("language", language)
                .queryParam("country", country)
                .queryParam("apiKey", API_KEY);
        return webClient.get().uri(uriComponentsBuilder.toUriString()).retrieve().bodyToMono(NewsApiResponse.class);
    }
}
