package com.example.githubrestapi.service;

import com.example.githubrestapi.model.commit.CommitResult;
import com.example.githubrestapi.model.contributor.ContributorResult;
import com.example.githubrestapi.model.error.BadRequest;
import com.example.githubrestapi.model.error.UnprocessableEntity;
import com.example.githubrestapi.model.repository.RepositorySearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Service
public class AppService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${github.api}")
    private String githubApiUrl;

    public ResponseEntity<?> searchRepositories(String q, String sort, String order, Integer perPage, Integer page) {
        String url = githubApiUrl + "/search/repositories?" + Optional.ofNullable(q).map(query -> "q=" + query + "&").orElse("")
                + Optional.ofNullable(sort).map(query -> "sort=" + query + "&").orElse("")
                + Optional.ofNullable(order).map(query -> "order=" + query + "&").orElse("")
                + Optional.ofNullable(perPage).map(query -> "per_page=" + query + "&").orElse("")
                + Optional.ofNullable(page).map(query -> "page=" + query + "&").orElse("");

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        try {
            if (result.getStatusCode().series() == CLIENT_ERROR || result.getStatusCode().series() == SERVER_ERROR) {
                return getIfError(result);
            } else {
                return new ResponseEntity<>(objectMapper.readValue(result.getBody(), RepositorySearchResult.class), result.getHeaders(), result.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getContributors(String owner, String repo) {
        String url = githubApiUrl + "/repos/" + owner + "/" + repo + "/stats/contributors";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        try {
            if (result.getStatusCode().series() == CLIENT_ERROR || result.getStatusCode().series() == SERVER_ERROR) {
                return getIfError(result);
            } else {
                return new ResponseEntity<>(objectMapper.readValue(result.getBody(), ContributorResult[].class), result.getHeaders(), result.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> getCommitsForProject(String owner, String repo, String sha, String path, String author,
                                                  String since, String until, Integer perPage, Integer page) {
        String url = githubApiUrl + "/repos/" + owner + "/" + repo + "/commits?" + Optional.ofNullable(sha).map(query -> "sha=" + query + "&").orElse("")
                + Optional.ofNullable(path).map(query -> "map=" + query + "&").orElse("")
                + Optional.ofNullable(author).map(query -> "author=" + query + "&").orElse("")
                + Optional.ofNullable(since).map(query -> "since=" + query + "&").orElse("")
                + Optional.ofNullable(until).map(query -> "until=" + query + "&").orElse("")
                + Optional.ofNullable(perPage).map(query -> "per_page=" + query + "&").orElse("")
                + Optional.ofNullable(page).map(query -> "page=" + query + "&").orElse("");
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        try {
            if (result.getStatusCode().series() == CLIENT_ERROR || result.getStatusCode().series() == SERVER_ERROR) {
                return getIfError(result);
            } else {
                return new ResponseEntity<>(objectMapper.readValue(result.getBody(), CommitResult[].class), result.getHeaders(), result.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private ResponseEntity<?> getIfError(ResponseEntity<String> result) throws JsonProcessingException {
        if (result.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return new ResponseEntity<>(objectMapper.readValue(result.getBody(), BadRequest.class), result.getHeaders(), result.getStatusCode());
        } else if (result.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            return new ResponseEntity<>(objectMapper.readValue(result.getBody(), UnprocessableEntity.class), result.getStatusCode());
        } else {
            return new ResponseEntity<>(result.getBody(), result.getHeaders(), result.getStatusCode());
        }
    }

}

