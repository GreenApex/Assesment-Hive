package com.ga.hive.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.persistence.entity.TaskDTO;
import com.ga.hive.persistence.mapper.ICategoryMapper;
import com.ga.hive.persistence.mapper.IPrincipalMapper;
import com.ga.hive.persistence.mapper.ITaskMapper;
import com.ga.hive.service.ITaskService;

/**
 * The Class TaskServiceImpl.
 *
 * @author Shalaka Nayal
 */
@Service
public class TaskServiceImpl implements ITaskService {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    ICategoryMapper categorymapper;

    @Autowired
    IPrincipalMapper principalmapper;

    @Autowired
    ITaskMapper taskMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.ITaskService#assignTask(com.ga.hive.persistence.entity.AllotTask)
     */
    @Override
    public boolean assignTask(AllotTask task) throws GAException {
        LOGGER.info("AssignTask");
        return taskMapper.allotTaskToUser(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.ITaskService#getMyTasks(java.lang.String)
     */

    /**
     * Sets the categories.
     *
     * @param taskDTO the task dto
     * @param categories the categories
     * @return the task dto
     */
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

    /**
     * Sets the principles.
     *
     * @param taskDTO the task dto
     * @param principles the principles
     * @return the task dto
     */
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

    @Override
    public AllotTask getMyTasks(String userID) throws GAException {
        LOGGER.info("getMyTasks : " + userID);
        try {
            return taskMapper.getMyTasks(userID);
        } catch (Exception exception) {
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }
}
