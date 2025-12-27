package com.example.literisemobile.models;

public class User {
    private int userId;
    private String phoneNumber;
    private String fullName;
    private String password;
    private String role;
    private boolean isVerified;

    public User() {
    }

    public User(int userId, String phoneNumber, String fullName, String password, String role, boolean isVerified) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.isVerified = isVerified;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
