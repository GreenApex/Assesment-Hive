package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.User;

/**
 * The Interface IUserMapper.
 *
 * @author Shalaka
 */
public interface IUserMapper {

    /**
     * Gets the user by email.
     *
     * @param email the email
     * @return the user by email
     * @throws GAException the GA exception
     */
    User getUserByEmail(String email) throws GAException;

    /**
     * Save user.
     *
     * @param user the user
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean saveUser(User user) throws GAException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean updateUser(User user) throws GAException;

    /**
     * Gets the user by id.
     *
     * @param userID the user id
     * @return the user by id
     * @throws GAException the GA exception
     */
    User getUserByID(String userID) throws GAException;

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
     * Make user inactive.
     *
     * @param email the email
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean makeUserInactive(String email) throws GAException;

}
