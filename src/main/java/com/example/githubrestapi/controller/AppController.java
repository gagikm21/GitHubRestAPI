package com.example.githubrestapi.controller;

import com.example.githubrestapi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/github")
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping(value = "/search/repositories")
    public String searchRepositories(@RequestParam(required = false) String q, @RequestParam(required = false) String sort,
                                     @RequestParam(required = false) String order, @RequestParam(required = false) String per_page,
                                     @RequestParam(required = false) String page){
        return appService.searchRepositories(q, sort, order, per_page, page);
    }

    @GetMapping(value = "/repos/{owner}/{repo}/contributors")
    public String getContributors(@PathVariable String owner, @PathVariable String repo){
        return appService.getContributors(owner, repo);
    }

    @GetMapping(value = "/repos/{owner}/{repo}/commits")
    public String getCommitsForProject(@PathVariable String owner, @PathVariable String repo){
        return appService.getCommitsForProject(owner, repo);
    }

}
