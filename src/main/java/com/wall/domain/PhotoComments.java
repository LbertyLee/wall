package com.wall.domain;


import java.util.Date;

public class PhotoComments {
    private Integer id;

    private String photoComments;

    private Integer photoId;

    private String commentUser;

    private Date commentTime;

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

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

    public String getPhotoComments() {
        return photoComments;
    }

    public void setPhotoComments(String photoComments) {
        this.photoComments = photoComments == null ? null : photoComments.trim();
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}