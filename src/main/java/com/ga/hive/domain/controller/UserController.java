package com.ga.hive.domain.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.User;
import com.ga.hive.service.IUserService;

/**
 * The Class UserController.
 *
 * @author Shalaka
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * Auth.
     *
     * @param email the email
     * @param password the password
     * @return the string
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public @ResponseBody String auth(@RequestParam("email") String email, @RequestParam("password") String password) {
        LOGGER.info("authentication controller");
        try {
            User user = userService.authenticateUser(email, password);

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, user);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    /**
     * Register.
     *
     * @param user the user
     * @return the string
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody User user) {
        LOGGER.info("authentication controller");
        try {
            Boolean registered = userService.registerUser(user);
            if (registered) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, registered);
            } else
                return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllUsers() {
        LOGGER.info("getAllUsers controller");
        try {
            List<User> users = userService.getAllUsers();
            if (users.size() <= 0) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "No users found");
            } else
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, users);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getActiveUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getActiveUsers() {
        LOGGER.info("getAllUsers controller");
        try {
            List<User> users = userService.getActiveUsers();
            if (users.size() <= 0) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "No users found");
            } else
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, users);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

}
