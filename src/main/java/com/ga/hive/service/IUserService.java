package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.User;

/**
 * The Interface IUserService.
 *
 * @author Shalaka
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

    List<User> getAllUsers() throws GAException;

    List<User> getActiveUsers() throws GAException;
}
