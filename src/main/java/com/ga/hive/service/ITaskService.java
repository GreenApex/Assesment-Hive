package com.ga.hive.service;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Task;
import com.ga.hive.persistence.entity.TaskDTO;

public interface ITaskService {

    boolean assignTask(Task task) throws GAException;

    TaskDTO getMyTasks(String userID) throws GAException;

}
