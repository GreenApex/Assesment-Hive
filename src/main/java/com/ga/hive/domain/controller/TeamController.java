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
import com.ga.hive.persistence.entity.Members;
import com.ga.hive.persistence.entity.Team;
import com.ga.hive.persistence.entity.TeamDTO;
import com.ga.hive.service.ITeamService;

@RestController
@RequestMapping(value = "/team")
public class TeamController {

    private static final Logger LOGGER = Logger.getLogger(TeamController.class);

    @Autowired
    ITeamService teamservice;

    @RequestMapping(value = "/assignusers", method = RequestMethod.POST)
    public @ResponseBody String assignTask(@RequestBody List<Members> membersList, @RequestParam("teamID") String teamID) {
        LOGGER.info("TeamController - assignTeam ");
        LOGGER.info("Team : " + membersList);

        try {
            teamservice.assignUsers(membersList, teamID);
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "Task Saved Successfully.");
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/addteam", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addteam(@RequestBody Team team) throws GAException {
        LOGGER.info("addteam controller");
        Boolean result = teamservice.addTeam(team);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, result);
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

    @RequestMapping(value = "/getallteam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllteam() throws GAException {
        LOGGER.info("getAllteam controller");
        try {
            List<Team> teamList = teamservice.getAllteam();
            if (teamList != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, teamList);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "Internal error");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getteambyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getTeamByID(@RequestParam("teamID") String teamID) throws GAException {
        LOGGER.info("get getTeamByID controller");
        try {
            Team team = teamservice.getTeamByID(teamID);
            if (team != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, team);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "No team found.");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    @RequestMapping(value = "/getTeamMembers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getTeamMembers(@RequestParam("teamID") String teamID) throws GAException {
        LOGGER.info("get getTeamMembers controller");
        try {
            TeamDTO teamDTO = teamservice.getTeamMembers(teamID);
            if (teamDTO != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, teamDTO);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, null);
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    @RequestMapping(value = "/updateteam", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateTeam(@RequestBody Team team, @RequestParam("teamID") String teamID)
            throws GAException {
        LOGGER.info("updateTeam controller");
        Boolean result = teamservice.updateTeam(team, teamID);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "team updated successfully");
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

    @RequestMapping(value = "/deleteteam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteteam(@RequestParam("teamID") String teamID) throws GAException {
        LOGGER.info("delete team controller");
        try {
            boolean result = teamservice.deleteTeamByID(teamID);
            if (!result) {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, false);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, true);
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

}