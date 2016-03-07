package com.ga.hive.persistence.entity;

import java.util.List;

/**
 * The Class TemplateDTO.
 *
 * @author Shalaka Nayal
 */
public class TemplateDTO {

    String templateID;
    String templateName;
    List<CategoryDTO> categoryList;
    String creationTime;

    /**
     * Gets the template id.
     *
     * @return the template id
     */
    public String getTemplateID() {
        return templateID;
    }

    /**
     * Sets the template id.
     *
     * @param templateID the new template id
     */
    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    /**
     * Gets the template name.
     *
     * @return the template name
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Sets the template name.
     *
     * @param templateName the new template name
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Gets the category list.
     *
     * @return the category list
     */
    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }

    /**
     * Sets the category list.
     *
     * @param categoryList the new category list
     */
    public void setCategoryList(List<CategoryDTO> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * Gets the creation time.
     *
     * @return the creation time
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation time.
     *
     * @param creationTime the new creation time
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((templateID == null) ? 0 : templateID.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TemplateDTO other = (TemplateDTO) obj;
        if (templateID == null) {
            if (other.templateID != null)
                return false;
        } else if (!templateID.equals(other.templateID))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TemplateDTO [templateID=" + templateID + ", templateName=" + templateName + ", categoryList="
                + categoryList + ", creationTime=" + creationTime + "]";
    }

}
