package com.ga.hive.persistence.entity;

public class Members {

    String memberID;
    String memberName;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "Memebers [memberID=" + memberID + ", memberName=" + memberName + "]";
    }

}
