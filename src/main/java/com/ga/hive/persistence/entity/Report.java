package com.ga.hive.persistence.entity;

public class Report {

    public String userID;
    public String templatename;
    public String categoryName;
    public String principleName;
    public String qaID;
    public String qaName;
    public String rating;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public String getQaID() {
        return qaID;
    }

    public void setQaID(String qaID) {
        this.qaID = qaID;
    }

    public String getQaName() {
        return qaName;
    }

    public void setQaName(String qaName) {
        this.qaName = qaName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Report [userID=" + userID + ", templatename=" + templatename + ", categoryName=" + categoryName
                + ", principleName=" + principleName + ", qaID=" + qaID + ", qaName=" + qaName + ", rating=" + rating
                + "]";
    }

}
