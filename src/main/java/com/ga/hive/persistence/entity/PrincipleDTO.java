package com.ga.hive.persistence.entity;

import java.util.List;

public class PrincipleDTO {

    public String principleID;
    public String principleName;
    public String catergoryName;
    public String templateName;
    public List<QuestionnaireDTO> questionnaireList;

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

    public List<QuestionnaireDTO> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<QuestionnaireDTO> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    public String getCatergoryName() {
        return catergoryName;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        return "PrincipleDTO [principleID=" + principleID + ", principleName=" + principleName + ", questionnaireList="
                + questionnaireList + "]";
    }

}
