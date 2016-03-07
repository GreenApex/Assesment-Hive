package com.ga.hive.persistence.entity;

import java.util.List;

public class Report {

    public String userID;
    public String templatename;
    public String templatedID;
    ReportCategory category;

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

    public String getTemplatedID() {
        return templatedID;
    }

    public void setTemplatedID(String templatedID) {
        this.templatedID = templatedID;
    }

    public void setCategory(ReportCategory category) {
        this.category = category;
    }

    public ReportCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Report [userID=" + userID + ", templatename=" + templatename + ", templatedID=" + templatedID
                + ", category=" + category + "]";
    }

}
