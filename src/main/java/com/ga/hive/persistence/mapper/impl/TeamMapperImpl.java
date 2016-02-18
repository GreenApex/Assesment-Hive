package com.ga.hive.persistence.mapper.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.Members;
import com.ga.hive.persistence.entity.Team;
import com.ga.hive.persistence.mapper.ITeamMapper;

@Repository
public class TeamMapperImpl implements ITeamMapper {

    private static final Logger LOGGER = Logger.getLogger(TeamMapperImpl.class);

    @Override
    public Boolean addTeam(Team team) throws GAException {
        LOGGER.info("addTeam " + team);
        try {
            Connection connection = DbManager.getConnection();
            String sql = "INSERT into team values ('" + team.getTeamID() + "','" + team.getTeamName() + "','"
                    + team.getTeamOwnerID() + "','" + team.getMembersID() + "', false)";
            Statement statement = connection.createStatement();
            LOGGER.info("stmt :" + statement);
            int rowsInserted = statement.executeUpdate(sql);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public List<Team> getAllTeam() throws GAException {
        LOGGER.info("getAllTeam ");
        List<Team> newTeamList = new ArrayList<Team>();
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery("Select * from team WHERE isDelete= false");

            while (res.next()) {
                Team team = new Team();
                team.setTeamID(res.getString("teamID"));
                team.setTeamName(res.getString("teamName"));
                team.setTeamOwnerID(res.getString("teamOwnerID"));
                team.setMembersID(res.getString("membersID"));
                team.setIsdelete(res.getBoolean("isdelete"));
                newTeamList.add(team);
                LOGGER.info("newteam :" + newTeamList.size());
            }
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
        return newTeamList;
    }

    @Override
    public Team getTeamByTeamID(String teamID) throws GAException {
        LOGGER.info("getTeamByTeamID  for " + teamID);
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM team WHERE teamID='" + teamID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No category Found.");
            }
            while (rs.next()) {
                String id = rs.getString("teamID");
                String teamName = rs.getString("teamName");
                String teamOwnerID = rs.getString("teamOwnerID");
                String memberIDs = rs.getString("membersID");
                boolean deletedFlag = rs.getBoolean("isdelete");

                Team team = new Team();
                team.setTeamID(id);
                team.setTeamName(teamName);
                team.setTeamOwnerID(teamOwnerID);
                team.setMembersID(memberIDs);
                team.setIsdelete(deletedFlag);

                DbManager.closeConnection(connection);
                LOGGER.info("result: " + team);
                return team;
            }
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public void updateTeam(Team dbTeam) throws GAException {
        LOGGER.info("updateTeam ");
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE team SET teamName = '" + dbTeam.getTeamName() + "' WHERE teamID='"
                    + dbTeam.getTeamID() + "'";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            LOGGER.info(i + " rows inserted");
            DbManager.closeConnection(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }

    }

    @Override
    public boolean deleteTeamByID(String teamID) throws GAException {
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE team SET isdelete = 1  WHERE teamID='" + teamID + "'";
            LOGGER.info("sql : " + sql);
            Statement statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(sql);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public Boolean assignUsers(List<Members> memberList, String teamID) throws GAException {
        try {
            List<String> userIDList = covertToList(memberList);
            String strUserList = userIDList.toString().replaceAll("[\\[\\]]*", "");
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE team SET membersid = '" + strUserList + "'  WHERE teamID='" + teamID + "'";
            LOGGER.info("sql : " + sql);
            Statement statement = connection.createStatement();
            int rowsInserted = statement.executeUpdate(sql);
            LOGGER.info(rowsInserted + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    List<String> covertToList(List<Members> members) {
        List<String> list = new ArrayList<>();
        for (Members member : members) {
            list.add(member.getMemberID());
        }
        return list;
    }
}
