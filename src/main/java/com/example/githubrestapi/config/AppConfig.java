package com.example.githubrestapi.config;

import com.example.githubrestapi.util.GithubApiResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Autowired
    GithubApiResponseErrorHandler errorHandler;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.errorHandler(errorHandler)
                .build();
    }

}
