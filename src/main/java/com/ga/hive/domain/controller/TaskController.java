package com.ga.hive.domain.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Task;
import com.ga.hive.persistence.entity.TaskDTO;
import com.ga.hive.service.ITaskService;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private static final Logger LOGGER = Logger.getLogger(TaskController.class);

    @Autowired
    ITaskService taskservice;

    @RequestMapping(value = "/assigntask", method = RequestMethod.POST)
    public @ResponseBody String assignTask(@RequestBody Task task) {
        LOGGER.info("TaskController - assignTask ");
        LOGGER.info("Task : " + task);

        try {
            taskservice.assignTask(task);
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "Task Saved Successfully.");
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getMyTask", method = RequestMethod.GET)
    public @ResponseBody String getMyTasks(@RequestParam("userID") String userID) {
        LOGGER.info("Getting task for : " + userID);

        try {
            TaskDTO dto = taskservice.getMyTasks(userID);
            if (dto.getCategories() == null && dto.getPrinciples() == null)
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "No job found for you.");
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, dto);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }
}
