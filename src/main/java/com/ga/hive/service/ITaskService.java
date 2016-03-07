package com.ga.hive.service;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.AllotTask;

/**
 * The Interface ITaskService.
 *
 * @author Shalaka Nayal
 */
public interface ITaskService {

    /**
     * Assign task.
     *
     * @param task the task
     * @return true, if successful
     * @throws GAException the GA exception
     */
    boolean assignTask(AllotTask task) throws GAException;

    /**
     * Gets the my tasks.
     *
     * @param userID the user id
     * @return the my tasks
     * @throws GAException the GA exception
     */
    AllotTask getMyTasks(String userID) throws GAException;

}
