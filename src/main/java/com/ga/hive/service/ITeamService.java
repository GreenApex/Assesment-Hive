package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Members;
import com.ga.hive.persistence.entity.Team;
import com.ga.hive.persistence.entity.TeamDTO;

public interface ITeamService {

    boolean assignUsers(List<Members> membersList, String teamID) throws GAException;

    Boolean addTeam(Team team) throws GAException;

    List<Team> getAllteam() throws GAException;

    Team getTeamByID(String teamID) throws GAException;

    Boolean updateTeam(Team team, String teamID) throws GAException;

    boolean deleteTeamByID(String teamID) throws GAException;

    TeamDTO getTeamMembers(String teamID) throws GAException;

}
