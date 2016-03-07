package com.ga.hive.persistence.mapper.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.User;
import com.ga.hive.persistence.mapper.IUserMapper;

/**
 * The Class UserMapperImpl.
 *
 * @author Shalaka Nayal
 */
@Repository
public class UserMapperImpl implements IUserMapper {

    private static final Logger LOGGER = Logger.getLogger(UserMapperImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#getUserByEmail(java.lang.String )
     */
    @Override
    public User getUserByEmail(String email) throws GAException {
        LOGGER.info("getUserByEmail for " + email);
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE email='" + email + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userID = rs.getString("userid");
                String name = rs.getString("name");
                String emailID = rs.getString("email");
                String password = rs.getString("password");
                boolean active = rs.getBoolean("active");

                User user = new User();
                user.setName(name);
                user.setUserID(userID);
                user.setEmail(emailID);
                user.setPassword(password);
                user.setAcitve(active);
                DbManager.closeConnection(connection);
                LOGGER.info("result: " + userID);
                return user;
            }
            return null;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#saveUser(com.ga.hive.persistence .entity.User)
     */
    @Override
    public Boolean saveUser(User user) throws GAException {

        LOGGER.info("save User " + user.getEmail());
        try {
            Connection connection = DbManager.getConnection();
            String query = "INSERT into users values('" + UUID.randomUUID().toString().replaceAll("-", "") + "','"
                    + user.getName() + "','" + user.getEmail() + "','" + user.getPassword() + "',true)";
            Statement stmt = connection.createStatement();
            int rowsInserted = stmt.executeUpdate(query);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#updateUser(com.ga.hive.persistence .entity.User)
     */
    @Override
    public Boolean updateUser(User user) throws GAException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#getUserByID()
     */
    @Override
    public User getUserByID(String userID) throws GAException {
        LOGGER.info("getUserByID for " + userID);
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE userid='" + userID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userid = rs.getString("userid");
                String name = rs.getString("name");
                String emailID = rs.getString("email");
                String password = rs.getString("password");
                boolean active = rs.getBoolean("active");

                User user = new User();
                user.setName(name);
                user.setUserID(userid);
                user.setEmail(emailID);
                user.setPassword(password);
                user.setAcitve(active);
                DbManager.closeConnection(connection);
                LOGGER.info("result: " + user);
                return user;
            }
            return null;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#getAllUsers()
     */
    @Override
    public List<User> getAllUsers() throws GAException {
        LOGGER.info("get All Users");
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userid = rs.getString("userid");
                String name = rs.getString("name");
                String emailID = rs.getString("email");
                String password = rs.getString("password");
                boolean active = rs.getBoolean("active");

                User user = new User();
                user.setName(name);
                user.setUserID(userid);
                user.setEmail(emailID);
                user.setPassword(password);
                user.setAcitve(active);

                LOGGER.info("result: " + user);
                users.add(user);

            }
            DbManager.closeConnection(connection);
            return users;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#getActiveUsers()
     */
    @Override
    public List<User> getActiveUsers() throws GAException {
        LOGGER.info("get Active Users");
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM users WHERE active=true";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            rs.getRow();
            while (rs.next()) {
                String userid = rs.getString("userid");
                String name = rs.getString("name");
                String emailID = rs.getString("email");
                String password = rs.getString("password");
                boolean active = rs.getBoolean("active");

                User user = new User();
                user.setName(name);
                user.setUserID(userid);
                user.setEmail(emailID);
                user.setPassword(password);
                user.setAcitve(active);
                LOGGER.info("result: " + user);
                users.add(user);

            }
            DbManager.closeConnection(connection);
            return users;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IUserMapper#makeUserInactive(java.lang.String)
     */
    @Override
    public Boolean makeUserInactive(String email) throws GAException {
        LOGGER.info("make User Inactive  for " + email);
        try {
            Connection connection = DbManager.getConnection();
            String query = "UPDATE users set active=false where email='" + email + "'";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(query);
            LOGGER.info(i + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }

    }
}
