package com.example.githubrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AppService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.api}")
    private String githubApiUrl;

    public String searchRepositories(String q, String sort, String order, String per_page, String page){
        String url = githubApiUrl + "/search/repositories?" + Optional.ofNullable(q).map(query -> "q=" + query + "&").orElse("")
                + Optional.ofNullable(sort).map(query -> "sort=" + query + "&").orElse("")
                + Optional.ofNullable(order).map(query -> "order=" + query + "&").orElse("")
                + Optional.ofNullable(per_page).map(query -> "per_page=" + query + "&").orElse("")
                + Optional.ofNullable(page).map(query -> "page=" + query + "&").orElse("");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }

    public String getContributors(String owner, String repo){
        String url = githubApiUrl + "/repos/" + owner + "/" + repo + "/stats/contributors";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }

    public String getCommitsForProject(String owner, String repo){
        String url = githubApiUrl + "/repos/" + owner + "/" + repo + "/commits";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }
}
