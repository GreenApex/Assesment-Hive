package com.ga.hive.persistence.entity;

import java.util.List;

public class Reviews {

    String templateID;
    String templateName;
    String reviewDate;
    String reviewerName;
    List<AnsQuestionnaire> ansQuestionnaires;

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

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public List<AnsQuestionnaire> getAnsQuestionnaires() {
        return ansQuestionnaires;
    }

    public void setAnsQuestionnaires(List<AnsQuestionnaire> ansQuestionnaires) {
        this.ansQuestionnaires = ansQuestionnaires;
    }

    @Override
    public String toString() {
        return "Reviews [templateID=" + templateID + ", templateName=" + templateName + ", reviewDate=" + reviewDate
                + ", reviewerName=" + reviewerName + ", ansQuestionnaires=" + ansQuestionnaires + "]";
    }

}
