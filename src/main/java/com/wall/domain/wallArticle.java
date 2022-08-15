package com.wall.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class wallArticle {
    @Id
    private Integer id;

    private Long userId;

    private String article;

    @JSONField(alternateNames = "yyyy-mm-dd HH:mm:ss")
    private Date articleTime;

    private String userNetName;

    private  String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUserNetName(String userNetName) {
        this.userNetName = userNetName;
    }

    public String getUserNetName() {
        return userNetName;
    }

    private String articleType;

    private Integer articleLike;

    private Integer articleComments;

    public Integer getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Integer articleComments) {
        this.articleComments = articleComments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article == null ? null : article.trim();
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }
    

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    public Integer getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(Integer articleLike) {
        this.articleLike = articleLike;
    }
}