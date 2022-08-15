package com.wall.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class wallPhoto {

    @Id
    private Integer id;

    private Long userId;

    private String photo;

    @JSONField(alternateNames = "yyyy-mm-dd HH:mm:ss")
    private Date photoTime;


    private String photoType;

    private Integer photoLike;

    private String userNetName;

    private  Integer photoComments;

    public Integer getPhotoComments() {
        return photoComments;
    }

    public void setPhotoComments(Integer photoComments) {
        this.photoComments = photoComments;
    }

    public String getUserNetName() {
        return userNetName;
    }

    public void setUserNetName(String userNetName) {
        this.userNetName = userNetName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
    }


    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType == null ? null : photoType.trim();
    }

    public Integer getPhotoLike() {
        return photoLike;
    }

    public void setPhotoLike(Integer photoLike) {
        this.photoLike = photoLike;
    }
}