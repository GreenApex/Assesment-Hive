package com.ga.hive.persistence.entity;

import java.util.List;

public class PrincipleDTO {

	private String principleID;
	private String principleName;
	private List<QuestionnaireDTO> questionnaireList;

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

	@Override
	public String toString() {
		return "PrincipleDTO [principleID=" + principleID + ", principleName="
				+ principleName + ", questionnaireList=" + questionnaireList
				+ "]";
	}

}
