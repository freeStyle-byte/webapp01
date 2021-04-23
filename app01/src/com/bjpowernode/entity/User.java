package com.bjpowernode.entity;

/**
 * @author freeStyle
 * @time 2021/4/16/16:41
 * @project idea-workspace
 */
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String gender;
    private String email;

    public User(Integer userId, String userName, String password, String gender, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.email = email;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
