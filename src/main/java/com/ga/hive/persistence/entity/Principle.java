package com.ga.hive.persistence.entity;

public class Principle {

    private String principleID;
    private String principleName;
    private String description;
    private String userID;
    private boolean disable;

    public String getPrincipleID() {
        return principleID;
    }

    public void setPrincipleID(String principleID) {
        this.principleID = principleID;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
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

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    @Override
    public String toString() {
        return "Principle [principleID=" + principleID + ", principleName=" + principleName + ", description="
                + description + ", userID=" + userID + ", disable=" + disable + "]";
    }

}
