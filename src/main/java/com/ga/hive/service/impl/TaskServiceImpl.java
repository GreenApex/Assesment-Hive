package com.ga.hive.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.AllotTask;
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

	private static final Logger LOGGER = Logger
			.getLogger(TaskServiceImpl.class);

	@Autowired
	ICategoryMapper categorymapper;

	@Autowired
	IPrincipalMapper principalmapper;

	@Autowired
	ITaskMapper taskMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ga.hive.service.ITaskService#assignTask(com.ga.hive.persistence.entity
	 * .AllotTask)
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
