package com.ga.hive.persistence.entity;

import java.util.List;

public class CategoryDTO {

	private String catrgoryID;
	private String catrgoryName;
	private List<PrincipleDTO> principleList;

	public String getCatrgoryID() {
		return catrgoryID;
	}

	public void setCatrgoryID(String catrgoryID) {
		this.catrgoryID = catrgoryID;
	}

	public String getCatrgoryName() {
		return catrgoryName;
	}

	public void setCatrgoryName(String catrgoryName) {
		this.catrgoryName = catrgoryName;
	}

	public List<PrincipleDTO> getPrincipleList() {
		return principleList;
	}

	public void setPrincipleList(List<PrincipleDTO> principleList) {
		this.principleList = principleList;
	}

	@Override
	public String toString() {
		return "CategoryDTO [catrgoryID=" + catrgoryID + ", catrgoryName="
				+ catrgoryName + ", principleList=" + principleList + "]";
	}

}
