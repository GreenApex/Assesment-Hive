package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.User;

/**
 * The Interface IUserService.
 *
 * @author Shalaka Nayal
 */
public interface IUserService {

    /**
     * Authenticate user.
     *
     * @param email the email
     * @param password the password
     * @return the user
     * @throws GAException the GA exception
     */
    User authenticateUser(String email, String password) throws GAException;

    /**
     * Register user.
     *
     * @param user the user
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean registerUser(User user) throws GAException;

    /**
     * Gets the all users.
     *
     * @return the all users
     * @throws GAException the GA exception
     */
    List<User> getAllUsers() throws GAException;

    /**
     * Gets the active users.
     *
     * @return the active users
     * @throws GAException the GA exception
     */
    List<User> getActiveUsers() throws GAException;

    /**
     * Delete users.
     *
     * @param email the email
     * @return true, if successful
     * @throws GAException the GA exception
     */
    boolean deleteUsers(String email) throws GAException;
}
