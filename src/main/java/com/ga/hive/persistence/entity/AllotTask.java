package com.ga.hive.persistence.entity;

import java.util.List;

public class AllotTask {

    public String userID;
    public String username;
    public List<CategoryDTO> categoryList;
    public List<PrincipleDTO> principleList;
    public boolean complete;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userID;
    }

    public void setUserid(String userID) {
        this.userID = userID;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<PrincipleDTO> getPrincipleList() {
        return principleList;
    }

    public void setPrincipleList(List<PrincipleDTO> principleList) {
        this.principleList = principleList;
    }

    @Override
    public String toString() {
        return "AllotTask [userID=" + userID + ", categoryList=" + categoryList + ", principleList=" + principleList
                + ", complete=" + complete + "]";
    }

}
