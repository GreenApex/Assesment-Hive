package com.ga.hive.persistence.entity;

public class QuestionnaireDTO {

    public String qID;
    public String name;
    public String comment;
    public String rating;
    public String attachment;
    public String principleName;
    public String categoryName;
    public String templateName;

    public String getqID() {
        return qID;
    }

    public void setqID(String qID) {
        this.qID = qID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    @Override
    public String toString() {
        return "QuestionnaireDTO [qID=" + qID + ", name=" + name + ", comment=" + comment + ", rating=" + rating
                + ", attachment=" + attachment + "]";
    }

}
