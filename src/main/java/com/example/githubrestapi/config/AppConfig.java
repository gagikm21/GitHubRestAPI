package com.example.githubrestapi.config;

import com.example.githubrestapi.util.GithubApiResponseErrorHandler;
import com.example.githubrestapi.util.HeaderRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Autowired
    private GithubApiResponseErrorHandler errorHandler;

    @Autowired
    private HeaderRequestInterceptor headerInterceptor;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.errorHandler(errorHandler)
                .interceptors(headerInterceptor)
                .build();
    }

}
