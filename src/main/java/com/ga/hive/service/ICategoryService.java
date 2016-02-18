package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Category;

public interface ICategoryService {

    Boolean addCategory(Category category) throws GAException;

    List<Category> getAllCategory() throws GAException;
    
    Category getCategoryByID(String categoryID) throws GAException;

    boolean deleteCategoryByID(String categoryID) throws GAException;

    Boolean updateCategoryID(Category category, String categoryID) throws GAException;

}
