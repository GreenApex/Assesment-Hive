package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.AllotTask;

/**
 * The Interface ITaskMapper.
 *
 * @author Shalaka Nayal
 */
public interface ITaskMapper {

    /**
     * Allot task to user.
     *
     * @param task the task
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean allotTaskToUser(AllotTask task) throws GAException;

    /**
     * Gets the my tasks.
     *
     * @param userID the user id
     * @return the my tasks
     * @throws GAException the GA exception
     */
    AllotTask getMyTasks(String userID) throws GAException;

    /**
     * Gets the my finished tasks.
     *
     * @param userID the user id
     * @return the my finished tasks
     * @throws GAException the GA exception
     */
    List<AllotTask> getMyFinishedTasks(String userID) throws GAException;

    /**
     * Gets the my pending tasks.
     *
     * @param userID the user id
     * @return the my pending tasks
     * @throws GAException the GA exception
     */
    List<AllotTask> getMyPendingTasks(String userID) throws GAException;
}
