package com.ga.hive.persistence.entity;

import java.util.List;

public class AnsTemplate {

    String userID;
    String templateID;
    String templateName;
    String creationTime;
    List<AnsQuestionnaire> ansQuestionnaire;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<AnsQuestionnaire> getAnsQuestionnaire() {
        return ansQuestionnaire;
    }

    public void setAnsQuestionnaire(List<AnsQuestionnaire> ansQuestionnaire) {
        this.ansQuestionnaire = ansQuestionnaire;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "AnsTemplate [userID=" + userID + ", templateID=" + templateID + ", templateName=" + templateName
                + ", ansQuestionnaire=" + ansQuestionnaire + "]";
    }

}
