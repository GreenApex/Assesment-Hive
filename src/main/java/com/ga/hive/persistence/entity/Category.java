package com.ga.hive.persistence.entity;


public class Category {

    private String catrgoryID;
    private String catrgoryName;
    private String description;
    private String userID;
    private boolean isDelete;

    public String getCatrgoryID() {
        return catrgoryID;
    }

    public void setCatrgoryID(String catrgoryID) {
        this.catrgoryID = catrgoryID;
    }

    public String getCatrgoryName() {
        return catrgoryName;
    }

    public void setCatrgoryName(String catrgoryName) {
        this.catrgoryName = catrgoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = false;
    }

    @Override
    public String toString() {
        return "Category catrgoryID=" + catrgoryID + ", catrgoryName=" + catrgoryName + ", description=" + description
                + ", userID=" + userID + ", isDelete=" + isDelete;
    }

}
