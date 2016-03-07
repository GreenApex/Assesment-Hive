package com.ga.hive.persistence.entity;

import java.util.List;

public class CategoryDTO {

    private String catergoryID;
    private String catergoryName;
    public String templateName;
    private List<PrincipleDTO> principleList;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getCatergoryID() {
        return catergoryID;
    }

    public void setCatergoryID(String catergoryID) {
        this.catergoryID = catergoryID;
    }

    public String getCatergoryName() {
        return catergoryName;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public List<PrincipleDTO> getPrincipleList() {
        return principleList;
    }

    public void setPrincipleList(List<PrincipleDTO> principleList) {
        this.principleList = principleList;
    }

    @Override
    public String toString() {
        return "CategoryDTO [catergoryID=" + catergoryID + ", catergoryName=" + catergoryName + ", templateName="
                + templateName + ", principleList=" + principleList + "]";
    }

}
