package com.wall.domain;


import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class sysUser implements Serializable {

    @Id
    private Long userId;

    private String userName;

    private String password;

    private String avatar;

    private Date createdAt;

    private String userType;

    private String userNetName;

    public String getUserNetName() {
        return userNetName;
    }

    public void setUserNetName(String userNetName) {
        this.userNetName = userNetName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}