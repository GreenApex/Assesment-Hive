package com.ga.hive.persistence.entity;

public class ReportCategory {

    public String categoryID;
    public String categoryName;
    public ReportPrinciple reportPrinciple;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ReportPrinciple getReportPrinciple() {
        return reportPrinciple;
    }

    public void setReportPrinciple(ReportPrinciple reportPrinciple) {
        this.reportPrinciple = reportPrinciple;
    }

    @Override
    public String toString() {
        return "ReportCategory [categoryID=" + categoryID + ", categoryName=" + categoryName + ", reportPrinciple="
                + reportPrinciple + "]";
    }

}
