package com.ga.hive.persistence.entity;

public class AnsQuestionnaire {

    String questionnnaireID;
    String questionnnaireName;
    String comment;
    String rating;

    public String getQuestionnnaireID() {
        return questionnnaireID;
    }

    public void setQuestionnnaireID(String questionnnaireID) {
        this.questionnnaireID = questionnnaireID;
    }

    public String getQuestionnnaireName() {
        return questionnnaireName;
    }

    public void setQuestionnnaireName(String questionnnaireName) {
        this.questionnnaireName = questionnnaireName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "AnsQuestionnaire [questionnnaireID=" + questionnnaireID + ", questionnnaireName=" + questionnnaireName
                + ", comment=" + comment + ", rating=" + rating + "]";
    }

}
