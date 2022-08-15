package com.wall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ArticleComments {
    private Integer id;

    private String articleComments;

    private Integer articleId;

    private String commentUser;


    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    @JSONField(alternateNames = "yyyy-mm-dd HH:mm:ss")
    private Date commentTime;

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(String articleComments) {
        this.articleComments = articleComments == null ? null : articleComments.trim();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }


}