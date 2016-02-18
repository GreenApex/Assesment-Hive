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
import com.ga.hive.persistence.entity.Questionnaire;
import com.ga.hive.persistence.mapper.IQuestionnarieMapper;

@Repository
public class QuestionnMapperImpl implements IQuestionnarieMapper {

    private static final Logger LOGGER = Logger.getLogger(QuestionnMapperImpl.class);

    static final String tablename = "questionnaire";

    @Override
    public Boolean addQuestionnarie(Questionnaire questionnaire) throws GAException {
        LOGGER.info("addQuestionnmarie " + questionnaire);
        try {
            Connection connection = DbManager.getConnection();
            String sql = "INSERT OVERWRITE TABLE " + tablename + " PARTITION (questionnaireid = '"
                    + questionnaire.getQuestionnaireID() + "') VALUES('" + questionnaire.getQuestionnaire() + "','"
                    + questionnaire.getDescription() + "', false)";
            Statement statement = connection.createStatement();
            LOGGER.info("stmt :" + sql);
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
    public List<Questionnaire> getAllQuestionnarie() throws GAException {
        LOGGER.info("getAllQuestionnarie ");
        List<Questionnaire> newQuestionnarieList = new ArrayList<Questionnaire>();
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet res = stmt.executeQuery("Select * from " + tablename+" WHERE disable=false");

            while (res.next()) {
                Questionnaire questionnaire = new Questionnaire();

                questionnaire.setQuestionnaire(res.getString("questionnairename"));
                questionnaire.setDescription(res.getString("description"));
                questionnaire.setQuestionnaireID(res.getString("questionnaireid"));
                questionnaire.setIsdelete(res.getBoolean("disable"));

                newQuestionnarieList.add(questionnaire);
                LOGGER.info("newcategoryList :" + newQuestionnarieList.size());
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
        return newQuestionnarieList;
    }

    @Override
    public Questionnaire getQuestionnarie(String questionnaireID) throws GAException {
        LOGGER.info("getQuestionnarie  for " + questionnaireID);
        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM " + tablename + " WHERE questionnaireid='" + questionnaireID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            if (rs == null) {
                throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No questionnaire Found.");
            }
            while (rs.next()) {

                String questionnaireName = rs.getString("questionnairename");
                String description = rs.getString("description");
                boolean deletedFlag = rs.getBoolean("disable");
                String ID = rs.getString("questionnaireid");

                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireID(ID);
                questionnaire.setQuestionnaire(questionnaireName);
                questionnaire.setDescription(description);
                questionnaire.setIsdelete(deletedFlag);

                DbManager.closeConnection(connection);
                LOGGER.info("result: " + questionnaire);
                return questionnaire;
            }
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    @Override
    public Boolean deleteQuestionnaireByID(String qid) throws GAException {
        try {
            Connection connection = DbManager.getConnection();

            Questionnaire dbQuestionnaire = getQuestionnarie(qid);
            String sql = "INSERT OVERWRITE TABLE " + tablename + " PARTITION (questionnaireid = '"
                    + dbQuestionnaire.getQuestionnaireID() + "') VALUES('" + dbQuestionnaire.getQuestionnaire() + "','"
                    + dbQuestionnaire.getDescription() + "', true)";
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
}
