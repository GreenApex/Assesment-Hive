package com.ga.hive.persistence.mapper.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.persistence.mapper.ICategoryMapper;

@Repository
public class CategoryMapperImpl implements ICategoryMapper {

    private static final Logger LOGGER = Logger.getLogger(CategoryMapperImpl.class);

    @Override
    public Boolean addCategory(Category category) throws GAException {
        LOGGER.info("addCategory " + category);
        try {
            Connection connection = DbManager.getConnection();
            String sql = "INSERT into category values ('" + category.getCatrgoryID() + "','"
                    + category.getCatrgoryName() + "','" + category.getDescription() + "','" + category.getUserID()
                    + "', false)";
            Statement statement = connection.createStatement();
            LOGGER.info("stmt :" + statement);
            int rowsInserted = statement.executeUpdate(sql);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public List<Category> getAllCategory() throws GAException {
        LOGGER.info("getAllCategory ");
        List<Category> newCategoryList = new ArrayList<Category>();
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery("Select * from category WHERE isdelete = false");

            while (res.next()) {
                Category newcategory = new Category();
                newcategory.setCatrgoryID(res.getString("catrgoryid"));
                newcategory.setCatrgoryName(res.getString("catrgoryname"));
                newcategory.setDescription(res.getString("description"));
                newcategory.setUserID(res.getString("userid"));
                newcategory.setDelete(res.getBoolean("isdelete"));
                newCategoryList.add(newcategory);
                LOGGER.info("newcategoryList :" + newCategoryList.size());
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
        return newCategoryList;
    }

    @Override
    public Category getCategoryByCategoryID(String catrgoryID) throws GAException {
        LOGGER.info("getCategoryByCategoryID  for " + catrgoryID);
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM category WHERE catrgoryid='" + catrgoryID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No category Found.");
            }
            while (rs.next()) {
                String categoryID = rs.getString("catrgoryID");
                String categoryName = rs.getString("catrgoryName");
                String description = rs.getString("description");
                String userString = rs.getString("userID");
                boolean deletedFlag = rs.getBoolean("isDelete");

                Category category = new Category();
                category.setCatrgoryID(categoryID);
                category.setCatrgoryName(categoryName);
                category.setDescription(description);
                category.setUserID(userString);
                category.setDelete(deletedFlag);

                DbManager.closeConnection(connection);
                LOGGER.info("result: " + category);
                return category;
            }
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public void updateCategory(Category dbCategory) throws GAException {
        LOGGER.info("update ");
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE category SET userID = '" + dbCategory.getUserID() + "' WHERE catrgoryid='"
                    + dbCategory.getCatrgoryID() + "'";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            LOGGER.info(i + " rows inserted");
            DbManager.closeConnection(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public boolean deleteCategoryByID(String categoryID) throws GAException {
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE category SET isdelete = true  WHERE catrgoryid='" + categoryID + "'";
            LOGGER.info("sql : " + sql);
            Statement statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(sql);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public void updateCatgoryData(Category dbCategory) throws GAException {
        LOGGER.info("update ");
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE category SET catrgoryname = '" + dbCategory.getCatrgoryName() + "', description='"
                    + dbCategory.getDescription() + "' WHERE catrgoryid='" + dbCategory.getCatrgoryID() + "'";
            LOGGER.info("SQL Query : " + sql);
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            LOGGER.info(i + " rows inserted");
            DbManager.closeConnection(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }

    }

    @Override
    public List<Category> getMyAssidnedCategories(String userID) throws GAException {
        LOGGER.info("getMyAssidnedCategories");
        List<Category> ultimateList = new ArrayList<Category>();
        try {
            List<Category> categories = getAllCategory();
            for (Category cat : categories) {
                String users = cat.getUserID();
                String[] usersList = users.split(",");
                for (String userid : usersList) {
                    if (userID.equals(userid))
                        ultimateList.add(cat);
                }
            }
            return ultimateList;

        } catch (Exception exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }
}
