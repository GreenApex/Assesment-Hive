package com.ga.hive.persistence.entity;

public class Questionnaire {

    private String questionnaireID;
    private String questionnaire;
    private String description;
    private boolean isdelete;

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "Questionnaire [questionnaireID=" + questionnaireID + ", questionnaire=" + questionnaire
                + ", description=" + description + ", isdelete=" + isdelete + "]";
    }

}