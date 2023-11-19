package com.example.quizapplication.model;

public class User {
    private long userId;
    private String fullName;
    private String nickName;
    private String dateOfBirth;
    private String email;

    public User(long userId, String fullName, String passwordHash) {
        this.userId = userId;
        this.fullName = fullName;
        this.passwordHash = passwordHash;
    }

    private String gender;



    private String passwordHash;


    public User(long userId, String fullName, String nickName, String dateOfBirth, String email, String gender, String passwordHash) {

        this.userId = userId;
        this.fullName = fullName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.passwordHash = passwordHash;
    }

    public long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
