package com.ga.hive.persistence.entity;

public class Task {

	private String catrgoryID;
	private String principleID;
	private String userID;

	public String getCatrgoryID() {
		return catrgoryID;
	}

	public void setCatrgoryID(String catrgoryID) {
		this.catrgoryID = catrgoryID;
	}

	public String getPrincipleID() {
		return principleID;
	}

	public void setPrincipleID(String principleID) {
		this.principleID = principleID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Task [catrgoryID=" + catrgoryID + ", principleID="
				+ principleID + ", userID=" + userID + "]";
	}

}
