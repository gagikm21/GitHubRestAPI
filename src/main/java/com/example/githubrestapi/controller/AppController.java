package com.example.githubrestapi.controller;

import com.example.githubrestapi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/github")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/search/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchRepositories(@RequestParam(required = false) String q, @RequestParam(required = false) String sort,
                                                @RequestParam(required = false) String order, @RequestParam(required = false, value = "per_page") Integer perPage,
                                                @RequestParam(required = false) Integer page) {
        return appService.searchRepositories(q, sort, order, perPage, page);
    }

    @GetMapping(value = "/repos/{owner}/{repo}/contributors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContributors(@PathVariable String owner, @PathVariable String repo) {
        return appService.getContributors(owner, repo);
    }

    @GetMapping(value = "/repos/{owner}/{repo}/commits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCommitsForProject(@PathVariable String owner, @PathVariable String repo,
                                                  @RequestParam(required = false) String sha, @RequestParam(required = false) String path,
                                                  @RequestParam(required = false) String author, @RequestParam(required = false) String since,
                                                  @RequestParam(required = false) String until, @RequestParam(required = false, value = "per_page") Integer perPage,
                                                  @RequestParam(required = false) Integer page) {
        return appService.getCommitsForProject(owner, repo, sha, path, author, since, until, perPage, page);
    }


}
