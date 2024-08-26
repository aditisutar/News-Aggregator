package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.helper.GNews;
import com.newsaggregator.model.NewsApiResponse;
import com.newsaggregator.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private GNews gNews;

    @Override
    public Mono<NewsApiResponse> getNewsArticle(UserPreference userPreference) {

        return gNews.fetchNews("None",userPreference.getCategory(),userPreference.getLanguage(),userPreference.getCountry());
    }
}
