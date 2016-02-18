package com.ga.hive.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.persistence.mapper.ICategoryMapper;
import com.ga.hive.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    ICategoryMapper categoryMapper;

    @Override
    public Boolean addCategory(Category category) throws GAException {
        LOGGER.info("createCategory: " + category);
        category.setCatrgoryID(UUID.randomUUID().toString().replaceAll("-", ""));
        category.setDelete(false);
        category.setUserID(null);
        return categoryMapper.addCategory(category);
    }

    @Override
    public List<Category> getAllCategory() throws GAException {
        List<Category> retrivedCategoryList = categoryMapper.getAllCategory();
        LOGGER.info("categoryList :" + retrivedCategoryList);
        return retrivedCategoryList;
    }

    @Override
    public Category getCategoryByID(String categoryID) throws GAException {
        Category dbCategory = categoryMapper.getCategoryByCategoryID(categoryID);
        LOGGER.info("dbCategory :" + dbCategory);
        return dbCategory;
    }

    @Override
    public boolean deleteCategoryByID(String categoryID) throws GAException {
        LOGGER.info("deleteCategoryByID :");
        Category dbCategory = getCategoryByID(categoryID);
        if (dbCategory == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No cat6egory found");
        } else {
            boolean deletecategory = categoryMapper.deleteCategoryByID(categoryID);
            return true;
        }
    }

    @Override
    public Boolean updateCategoryID(Category category, String categoryID) throws GAException {
        LOGGER.info("updateCategoryID by categoryID service called : " + categoryID);
        Category dbCategory = getCategoryByID(categoryID);
        dbCategory.setCatrgoryName(category.getCatrgoryName());
        dbCategory.setDescription(category.getDescription());
        categoryMapper.updateCatgoryData(dbCategory);
        return true;

    }

}
