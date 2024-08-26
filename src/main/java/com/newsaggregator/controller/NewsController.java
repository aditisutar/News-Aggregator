package com.newsaggregator.controller;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.exception.NoUserPreferenceFoundException;
import com.newsaggregator.helper.LoggedEmail;
import com.newsaggregator.model.NewsApiResponse;
import com.newsaggregator.service.NewsService;
import com.newsaggregator.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NewsController {
    @Autowired
    private LoggedEmail loggedEmail;
    @Autowired
    private UserPreferenceService userPreferenceService;
    @Autowired
    private NewsService newsService;
    @GetMapping("/news")
    public ResponseEntity<Mono<NewsApiResponse>> getNews(){
    String email=loggedEmail.getLoggedInEmail();
    UserPreference userPreference= userPreferenceService.getPreferences(email).orElseThrow(()->new NoUserPreferenceFoundException("User preference not found"));
    Mono<NewsApiResponse> apiResponseMono= newsService.getNewsArticle(userPreference);
    return new ResponseEntity<>(apiResponseMono, HttpStatus.OK);
    }

}
