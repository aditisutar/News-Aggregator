package com.newsaggregator.config;

import com.newsaggregator.helper.LoggedEmail;
import org.slf4j.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Configurations {
     @Bean
    public Logger log(){
         return LoggerFactory.getLogger("ApplicationLogger");
    }

    @Bean
    public RestTemplate restTemplate(){
         return new RestTemplate();
    }

    @Bean
    public WebClient webClient(){
         return  WebClient.builder().build();
    }


}
