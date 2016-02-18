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
     * @return the user by id
     * @throws GAException the GA exception
     */
    User getUserByID(String userID) throws GAException;

    List<User> getAllUsers() throws GAException;

    List<User> getActiveUsers() throws GAException;

}
