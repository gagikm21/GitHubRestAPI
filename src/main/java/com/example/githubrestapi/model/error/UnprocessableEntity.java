package com.example.githubrestapi.model.error;

import java.util.List;

public class UnprocessableEntity {

    private String message;

    public List<GitHubError> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GitHubError> getErrors() {
        return errors;
    }

    public void setErrors(List<GitHubError> errors) {
        this.errors = errors;
    }
}
