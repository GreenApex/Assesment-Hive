package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Category;

public interface ICategoryMapper {

    Boolean addCategory(Category category) throws GAException;

    List<Category> getAllCategory() throws GAException;

    Category getCategoryByCategoryID(String catrgoryID) throws GAException;

    void updateCategory(Category dbCategory) throws GAException;

    boolean deleteCategoryByID(String categoryID) throws GAException;

    public void updateCatgoryData(Category dbCategory) throws GAException;

    List<Category> getMyAssidnedCategories(String userID) throws GAException;

}
