package com.ga.hive.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.User;
import com.ga.hive.persistence.mapper.IUserMapper;
import com.ga.hive.service.IUserService;

/**
 * The Class UserServiceImpl.
 *
 * @author Shalaka Nayal
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    IUserMapper userMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IUserService#authenticateUser(java.lang.String, java.lang.String)
     */
    @Override
    public User authenticateUser(String email, String password) throws GAException {
        LOGGER.info("authenticateUser: " + email + "," + password);
        User user = userMapper.getUserByEmail(email);
        if (user == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND);
        }
        if (!user.isAcitve()) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND);
        }
        if (user.getPassword().equals(password)) {

            LOGGER.info("User Authenticated");
            return user;
        } else {
            throw new GAException(ErrorCodes.GA_AUTH_FAILED);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IUserService#registerUser(com.ga.hive.persistence .entity.User)
     */
    @Override
    public Boolean registerUser(User user) throws GAException {
        LOGGER.info("registerUser: " + user);
        User retrievedUser = userMapper.getUserByEmail(user.getEmail());
        if (retrievedUser == null) {
            LOGGER.info("No user found by : " + user.getEmail());
            /**
             * Thus we gonna register
             */
            return userMapper.saveUser(user);
        } else {
            throw new GAException(ErrorCodes.GA_USER_ALREADY_REGISTERED);
        }
    }

    @Override
    public List<User> getAllUsers() throws GAException {
        LOGGER.info("getAllUsers");
        List<User> users = userMapper.getAllUsers();
        if (users == null) {
            LOGGER.info("No user found by");
            return null;
        } else {
            return users;
        }
    }

    @Override
    public List<User> getActiveUsers() throws GAException {
        LOGGER.info("getAllUsers");
        List<User> users = userMapper.getActiveUsers();
        if (users == null) {
            LOGGER.info("No user found by");
            return null;
        } else {
            return users;
        }
    }

    @Override
    public boolean deleteUsers(String email) throws GAException {
        LOGGER.info("deleteUsers: " + email);
        return userMapper.makeUserInactive(email);
    }
}
