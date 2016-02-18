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
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.persistence.mapper.IPrincipalMapper;

@Repository
public class PrincipalMapperImpl implements IPrincipalMapper {

    private static final Logger LOGGER = Logger.getLogger(PrincipalMapperImpl.class);

    @Override
    public Principle getPrincipalByprincipalID(String principleID) throws GAException {
        LOGGER.info("getPrincipalByprincipalID  for " + principleID);
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM principal WHERE principleid='" + principleID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Principle Found..");
            }

            while (rs.next()) {
                String dbPID = rs.getString("principleid");
                String principleName = rs.getString("principlename");
                String description = rs.getString("description");
                String userString = rs.getString("userid");
                boolean isdelete = rs.getBoolean("isdelete");

                Principle principle = new Principle();
                principle.setPrincipleID(dbPID);
                principle.setPrincipleName(principleName);
                principle.setDescription(description);
                principle.setUserID(userString);
                principle.setDisable(isdelete);
                DbManager.closeConnection(connection);
                LOGGER.info("result: " + principle);
                return principle;
            }
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public Boolean createprincipal(Principle principal) throws GAException {
        LOGGER.info("createPrincipal called for : " + principal);
        try {

            Connection connection = DbManager.getConnection();
            String sql = "INSERT into principal values ('" + principal.getPrincipleID() + "','"
                    + principal.getPrincipleName() + "','" + principal.getDescription() + "','" + principal.getUserID()
                    + "', false)";
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sql);
            LOGGER.info(i + " rows inserted");
            DbManager.closeConnection(connection);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public void updatePrincipal(Principle dbPrincipal) throws GAException {
        LOGGER.info("update ");
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE principal SET userid = '" + dbPrincipal.getUserID() + "' WHERE principleid='"
                    + dbPrincipal.getPrincipleID() + "'";
            LOGGER.info("SQL Query : " + sql);
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
    public List<Principle> getAllPrincipal() throws GAException {
        LOGGER.info("getAllprincipal ");
        List<Principle> newPrincipalList = new ArrayList<Principle>();
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery("Select * from principal WHERE isdelete = false");

            while (res.next()) {
                Principle principal = new Principle();
                principal.setPrincipleID(res.getString("principleID"));
                principal.setPrincipleName(res.getString("principleName"));
                principal.setDescription(res.getString("description"));
                principal.setUserID(res.getString("userID"));
                newPrincipalList.add(principal);
                LOGGER.info("newPrincipal :" + newPrincipalList.size());
            }
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
        return newPrincipalList;
    }

    @Override
    public void updatePrincipalData(Principle dbPrincipal) throws GAException {
        LOGGER.info("update ");
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE principal SET principlename = '" + dbPrincipal.getPrincipleName() + "', description='"
                    + dbPrincipal.getDescription() + "' WHERE principleid='" + dbPrincipal.getPrincipleID() + "'";
            LOGGER.info("SQL Query : " + sql);
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
    public boolean deletePrincipleByID(String principleID) throws GAException {
        try {
            Connection connection = DbManager.getConnection();
            String sql = "UPDATE principal SET isdelete = true  WHERE principleid='" + principleID + "'";
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
    public List<Principle> getMyAssidnedPrinciples(String userid) throws GAException {

        LOGGER.info("getMyAssidnedPrinciples");
        List<Principle> ultimateList = new ArrayList<Principle>();
        try {
            List<Principle> principles = getAllPrincipal();
            for (Principle principle : principles) {
                String users = principle.getUserID();
                String[] usersList = users.split(",");
                for (String userID : usersList) {
                    if (userID.equals(userid))
                        ultimateList.add(principle);
                }
            }
            return ultimateList;

        } catch (Exception exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }
}
