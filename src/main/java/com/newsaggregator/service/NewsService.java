package com.newsaggregator.service;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.model.NewsApiResponse;
import reactor.core.publisher.Mono;

public interface NewsService {
    Mono<NewsApiResponse> getNewsArticle(UserPreference userPreference);
}
