package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Members;
import com.ga.hive.persistence.entity.Team;

public interface ITeamMapper {

    Boolean addTeam(Team team) throws GAException;

    List<Team> getAllTeam() throws GAException;

    Team getTeamByTeamID(String teamID) throws GAException;

    void updateTeam(Team dbTeam) throws GAException;

    boolean deleteTeamByID(String teamID) throws GAException;

    Boolean assignUsers(List<Members> userIDList, String teamID) throws GAException;

}
