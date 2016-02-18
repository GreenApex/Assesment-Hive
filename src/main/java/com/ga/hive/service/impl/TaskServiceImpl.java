package com.ga.hive.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.persistence.entity.Task;
import com.ga.hive.persistence.entity.TaskDTO;
import com.ga.hive.persistence.mapper.ICategoryMapper;
import com.ga.hive.persistence.mapper.IPrincipalMapper;
import com.ga.hive.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    ICategoryMapper categorymapper;

    @Autowired
    IPrincipalMapper principalmapper;

    @Override
    public boolean assignTask(Task task) throws GAException {
        LOGGER.info("ServiceImpl -assignTask");
        if (task.getCatrgoryID() != null && task.getPrincipleID() == null) {
            LOGGER.info("categoryID is : " + task.getCatrgoryID());
            Category dbCategory = categorymapper.getCategoryByCategoryID(task.getCatrgoryID());
            List<String> userIDList = new ArrayList<String>();
            LOGGER.info("inside else");
            String userIds = dbCategory.getUserID();

            if (userIds.equalsIgnoreCase("null")) {
                String newUserIDString = new String();
                newUserIDString = task.getUserID();
                userIDList.add(newUserIDString);
            } else {
                String[] stringArray = userIds.split(",");
                userIDList = Arrays.asList(stringArray);
                userIDList.add(task.getUserID());
            }
            LOGGER.info(userIDList);
            String userIDsString = userIDList.toString();
            userIDsString = userIDsString.replaceAll("[\\[\\]]*", "");
            dbCategory.setUserID(userIDsString);

            categorymapper.updateCategory(dbCategory);

        }
        if (task.getPrincipleID() != null && task.getCatrgoryID() == null) {
            LOGGER.info("categoryID is : " + task.getPrincipleID());
            Principle dbPrincipal = principalmapper.getPrincipalByprincipalID(task.getPrincipleID());
            List<String> userIDList = new ArrayList<String>();
            String userIds = dbPrincipal.getUserID();
            if (userIds.equalsIgnoreCase("null")) {
                String newUserIDString = new String();
                newUserIDString = task.getUserID();
                userIDList.add(newUserIDString);
            } else {
                String[] stringArray = userIds.split(",");
                userIDList = Arrays.asList(stringArray);
                userIDList.add(task.getUserID());
            }
            LOGGER.info(userIDList);
            String userIDsString = userIDList.toString();
            userIDsString = userIDsString.replaceAll("[\\[\\]]*", "");
            dbPrincipal.setUserID(userIDsString);
            principalmapper.updatePrincipal(dbPrincipal);
        }

        if (task.getCatrgoryID() == null && task.getPrincipleID() == null) {
            throw new GAException(ErrorCodes.GA_MANDATORY_PARAMETERS_NOT_SET);
        }

        return true;
    }

    @Override
    public TaskDTO getMyTasks(String userID) throws GAException {
        LOGGER.info("getMyTasks : " + userID);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setUserID(userID);
        try {
            List<Principle> principles = principalmapper.getMyAssidnedPrinciples(userID);
            taskDTO = setPrinciples(taskDTO, principles);

            List<Category> categories = categorymapper.getMyAssidnedCategories(userID);
            taskDTO = setCategories(taskDTO, categories);

            return taskDTO;
        } catch (Exception exception) {
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    private TaskDTO setCategories(TaskDTO taskDTO, List<Category> categories) {
        LOGGER.info("Setting Categories");
        if (categories == null)
            taskDTO.setCategories(null);
        else {
            Map<String, Category> catMap = new HashMap<String, Category>();
            for (Category cat : categories) {
                catMap.put(cat.getCatrgoryID(), cat);
            }
            taskDTO.setCategories(catMap);
        }

        return taskDTO;
    }

    TaskDTO setPrinciples(TaskDTO taskDTO, List<Principle> principles) {
        LOGGER.info("Setting principles");
        if (principles == null)
            taskDTO.setPrinciples(null);
        else {
            Map<String, Principle> princiMap = new HashMap<String, Principle>();
            for (Principle principle : principles) {
                princiMap.put(principle.getPrincipleID(), principle);
            }
            taskDTO.setPrinciples(princiMap);
        }

        return taskDTO;
    }
}
