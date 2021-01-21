package com.example.githubrestapi.model.commit;

import com.example.githubrestapi.model.Parent;
import com.example.githubrestapi.model.User;

import java.util.List;

public class CommitResult {

    private String url;
    private String sha;
    private String nodeId;
    private String htmlUrl;
    private String commentsUrl;
    private Commit commit;
    private User author;
    private User committer;
    private List<Parent> parents;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getCommitter() {
        return committer;
    }

    public void setCommitter(User committer) {
        this.committer = committer;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
