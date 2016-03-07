package com.ga.hive.persistence.entity;

public class ReportQA {

    public String qaID;
    public String qaName;
    public String rating;

    @Override
    public String toString() {
        return "ReportQA [qaID=" + qaID + ", qaName=" + qaName + ", rating=" + rating + "]";
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

}
