package com.ga.hive.persistence.entity;

public class User {

    private String userID;
    private String name;
    private String email;
    private String password;
    private boolean acitve;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcitve() {
        return acitve;
    }

    public void setAcitve(boolean acitve) {
        this.acitve = acitve;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", password=" + password
                + ", acitve=" + acitve + "]";
    }

}
