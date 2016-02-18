package com.ga.hive.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Members;
import com.ga.hive.persistence.entity.Team;
import com.ga.hive.persistence.entity.TeamDTO;
import com.ga.hive.persistence.entity.User;
import com.ga.hive.persistence.mapper.ITeamMapper;
import com.ga.hive.persistence.mapper.IUserMapper;
import com.ga.hive.service.ITeamService;

@Service
public class TeamServiceImpl implements ITeamService {

    private static final Logger LOGGER = Logger.getLogger(TeamServiceImpl.class);

    @Autowired
    ITeamMapper teammapper;

    @Autowired
    IUserMapper userMapper;

    @Override
    public boolean assignUsers(List<Members> userIDList, String teamID) throws GAException {
        LOGGER.info("userIDList : " + userIDList);
        teammapper.assignUsers(userIDList, teamID);
        return true;

    }

    @Override
    public Boolean addTeam(Team team) throws GAException {
        LOGGER.info("addTeam: " + team);
        team.setTeamID(UUID.randomUUID().toString().replaceAll("-", ""));
        team.setMembersID(null);
        team.setIsdelete(false);
        return teammapper.addTeam(team);
    }

    @Override
    public List<Team> getAllteam() throws GAException {
        List<Team> retrivedTeamList = teammapper.getAllTeam();
        LOGGER.info("teamList :" + retrivedTeamList);
        return retrivedTeamList;
    }

    @Override
    public Team getTeamByID(String teamID) throws GAException {
        Team dbTeam = teammapper.getTeamByTeamID(teamID);
        LOGGER.info("dbCategory :" + dbTeam);
        return dbTeam;
    }

    @Override
    public Boolean updateTeam(Team team, String teamID) throws GAException {
        LOGGER.info("updateTeam by teamID service called : " + teamID);
        Team dbTeam = getTeamByID(teamID);
        dbTeam.setTeamName(team.getTeamName());
        teammapper.updateTeam(dbTeam);
        return true;
    }

    @Override
    public boolean deleteTeamByID(String teamID) throws GAException {
        LOGGER.info("deleteTeamByID :" + teamID);
        Team dbTeam = getTeamByID(teamID);
        if (dbTeam == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Team found");
        } else {
            return teammapper.deleteTeamByID(teamID);
        }
    }

    @Override
    public TeamDTO getTeamMembers(String teamID) throws GAException {
        LOGGER.info("getTeamMembers :" + teamID);
        Team dbTeam = teammapper.getTeamByTeamID(teamID);
        if (dbTeam == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Team found");
        } else {
            if (dbTeam.getMembersID().isEmpty() || dbTeam.getMembersID().equals("null"))
                return null;
            else {
                TeamDTO teamDTO = new TeamDTO();
                List<Members> membersList = new ArrayList<Members>();
                String strUserList = dbTeam.getMembersID();
                strUserList = strUserList.replace(" ", "");
                String[] userList = strUserList.split(",");
                for (String userId : userList) {
                    User user = userMapper.getUserByID(userId);
                    Members members = new Members();
                    members.setMemberID(userId);
                    members.setMemberName(user.getName());
                    membersList.add(members);
                }
                teamDTO.setTeamID(teamID);
                teamDTO.setTeamName(dbTeam.getTeamName());
                teamDTO.setTeamOwnerID(dbTeam.getTeamOwnerID());
                teamDTO.setMembers(membersList);
                return teamDTO;
            }
        }
    }
}
