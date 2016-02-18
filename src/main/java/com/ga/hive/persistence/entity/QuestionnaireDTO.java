package com.ga.hive.persistence.entity;

public class QuestionnaireDTO {

	private String qID;
	private String name;
	private String comment;
	private String rating;

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

	@Override
	public String toString() {
		return "QuestionnaireDTO [qID=" + qID + ", name=" + name + ", comment="
				+ comment + ", rating=" + rating + "]";
	}

}
