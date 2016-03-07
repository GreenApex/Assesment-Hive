package com.ga.hive.persistence.mapper.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.persistence.entity.CategoryDTO;
import com.ga.hive.persistence.entity.PrincipleDTO;
import com.ga.hive.persistence.entity.TaskTemplate;
import com.ga.hive.persistence.mapper.ITaskMapper;
import com.google.gson.Gson;

/**
 * The Class TaskMapperImpl.
 *
 * @author Shalaka Nayal
 */
@Repository
public class TaskMapperImpl implements ITaskMapper {

    private static final Logger LOGGER = Logger.getLogger(TaskMapperImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.ITaskMapper#allotTaskToUser(com.ga.hive.persistence.entity.AllotTask)
     */
    @Override
    public Boolean allotTaskToUser(AllotTask task) throws GAException {

        LOGGER.info("Allottask to user: " + task.getUserid());
        String userid = task.getUserid();
        try {
            Gson gson = new Gson();

            List<CategoryDTO> list = task.getCategoryList();
            Iterator<CategoryDTO> iterator = list.iterator();

            String sql = "INSERT into allottask values";
            int index = 0;
            while (iterator.hasNext()) {

                CategoryDTO category = iterator.next();
                String categoryString = gson.toJson(category);
                if (index < 1) {
                    sql = sql + "('" + categoryString + "','','" + userid + "', false)";
                } else {
                    sql = sql + ",('" + categoryString + "','','" + userid + "', false)";
                }
                index++;
            }
            List<PrincipleDTO> princiList = task.getPrincipleList();
            Iterator<PrincipleDTO> iterator2 = princiList.iterator();
            while (iterator2.hasNext()) {
                PrincipleDTO princi = iterator2.next();
                String princiString = gson.toJson(princi);
                sql = sql + ",('','" + princiString + "','" + userid + "', false)";
            }
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            LOGGER.info("********************************" + sql + "**************************");
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
    public AllotTask getMyTasks(String userID) throws GAException {
        LOGGER.info("getMyTasks: " + userID);
        Gson gson = new Gson();
        try {

            Connection connection = DbManager.getConnection();
            String sql = "Select * from allottask where userid=" + userID;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs == null) {
                return null;
            }

            AllotTask task = new AllotTask();
            task.setUserid(userID);

            List<CategoryDTO> cats = new ArrayList<CategoryDTO>();
            List<PrincipleDTO> princies = new ArrayList<PrincipleDTO>();
            while (rs.next()) {
                String categoryStr = rs.getString("category");
                String principleStr = rs.getString("principle");
                if (!categoryStr.isEmpty()) {
                    CategoryDTO category = gson.fromJson(categoryStr, CategoryDTO.class);
                    cats.add(category);
                } else if (!principleStr.isEmpty()) {
                    PrincipleDTO principle = gson.fromJson(principleStr, PrincipleDTO.class);
                    princies.add(principle);
                }

            }

            Iterator<PrincipleDTO> iterator = princies.iterator();
            while (iterator.hasNext()) {
                PrincipleDTO principleDTO = iterator.next();

                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCatergoryName(principleDTO.getCatergoryName());
                List<PrincipleDTO> list = new ArrayList<PrincipleDTO>();
                list.add(principleDTO);
                categoryDTO.setPrincipleList(list);
                cats.add(categoryDTO);
            }
            task.setCategoryList(cats);
            task.setPrincipleList(null);
            DbManager.closeConnection(connection);
            return task;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public List<AllotTask> getMyPendingTasks(String userID) throws GAException {
        LOGGER.info("getMyTasks: " + userID);
        Gson gson = new Gson();
        try {

            Connection connection = DbManager.getConnection();
            String sql = "Select * from allottask where userid=" + userID + " and complete=" + false;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Principle Found..");
            }

            List<AllotTask> tasks = new ArrayList<AllotTask>();
            while (rs.next()) {
                AllotTask task = new AllotTask();
                task.setUserid(userID);
                String temp = rs.getString("tasktemplate");
                TaskTemplate taskTemplate = gson.fromJson(temp, TaskTemplate.class);
                // task.setTaskTemplate(taskTemplate);
                task.setComplete(rs.getBoolean("complete"));
                tasks.add(task);
            }
            DbManager.closeConnection(connection);
            return tasks;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public List<AllotTask> getMyFinishedTasks(String userID) throws GAException {
        LOGGER.info("getMyTasks: " + userID);
        Gson gson = new Gson();
        try {

            Connection connection = DbManager.getConnection();
            String sql = "Select * from allottask where userid=" + userID + " and complete=" + true;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Principle Found..");
            }

            List<AllotTask> tasks = new ArrayList<AllotTask>();
            while (rs.next()) {
                AllotTask task = new AllotTask();
                task.setUserid(userID);
                String temp = rs.getString("tasktemplate");
                TaskTemplate taskTemplate = gson.fromJson(temp, TaskTemplate.class);
                // task.setTaskTemplate(taskTemplate);
                task.setComplete(rs.getBoolean("complete"));
                tasks.add(task);
            }
            DbManager.closeConnection(connection);
            return tasks;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }
}
