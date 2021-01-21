package com.example.githubrestapi.model.contributor;

import com.example.githubrestapi.model.User;
import com.example.githubrestapi.model.Week;

import java.util.List;

public class ContributorResult {

    private User author;
    private int total;
    private List<Week> weeks;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }
}
