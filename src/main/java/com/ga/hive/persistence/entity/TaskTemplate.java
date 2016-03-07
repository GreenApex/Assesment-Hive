package com.ga.hive.persistence.entity;

import java.util.List;

public class TaskTemplate {

    public String templateID;
    public String catrgoryName;
    public String templateName;
    public List<PrincipleDTO> principleList;

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

    public List<PrincipleDTO> getPrincipleList() {
        return principleList;
    }

    public void setPrincipleList(List<PrincipleDTO> principleList) {
        this.principleList = principleList;
    }

}
