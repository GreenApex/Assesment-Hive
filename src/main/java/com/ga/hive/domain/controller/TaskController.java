package com.ga.hive.domain.controller;

import java.util.Iterator;
import java.util.List;

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
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.service.ITaskService;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private static final Logger LOGGER = Logger.getLogger(TaskController.class);

    @Autowired
    ITaskService taskservice;

    @RequestMapping(value = "/assigntask", method = RequestMethod.POST)
    public @ResponseBody String assignTask(@RequestBody List<AllotTask> tasks) {
        LOGGER.info("assignTask ");

        try {
            Iterator<AllotTask> iterator = tasks.iterator();
            while (iterator.hasNext()) {

                AllotTask task = iterator.next();

                LOGGER.info("Task for " + task.getUserid());

                if ((task.getCategoryList() != null || task.getPrincipleList() != null)
                        && (task.getCategoryList().size() > 0 || task.getPrincipleList().size() > 0)) {
                    taskservice.assignTask(task);
                } else {
                    LOGGER.info(task.getUserid() + " has no tasks");
                }
            }

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "Task Saved Successfully.");
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getMyTasks", method = RequestMethod.GET)
    public @ResponseBody String getMyTasks(@RequestParam("userID") String userID) {
        LOGGER.info("Getting task for : " + userID);

        try {
            AllotTask task = taskservice.getMyTasks(userID);
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, task);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

}
