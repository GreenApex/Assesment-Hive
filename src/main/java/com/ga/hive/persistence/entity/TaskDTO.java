package com.ga.hive.persistence.entity;

import java.util.Map;

public class TaskDTO {

    String userID;
    Map<String, Category> categories;
    Map<String, Principle> principles;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Category> categories) {
        this.categories = categories;
    }

    public Map<String, Principle> getPrinciples() {
        return principles;
    }

    public void setPrinciples(Map<String, Principle> principles) {
        this.principles = principles;
    }

    @Override
    public String toString() {
        return "TaskDTO [userID=" + userID + ", categories=" + categories + ", principles=" + principles + "]";
    }

}
