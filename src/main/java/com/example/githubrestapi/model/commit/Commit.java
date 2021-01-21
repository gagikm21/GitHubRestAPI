package com.example.githubrestapi.model.commit;

import com.example.githubrestapi.model.Tree;
import com.example.githubrestapi.model.UserShort;
import com.example.githubrestapi.model.Verification;

public class Commit {

    private String url;
    private UserShort author;
    private UserShort committer;
    private String message;
    private Tree tree;
    private int commentCount;
    private Verification verification;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserShort getAuthor() {
        return author;
    }

    public void setAuthor(UserShort author) {
        this.author = author;
    }

    public UserShort getCommitter() {
        return committer;
    }

    public void setCommitter(UserShort committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }
}
