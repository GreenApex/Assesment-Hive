package com.ga.hive.persistence.mapper.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.persistence.entity.QuestionnaireDTO;
import com.ga.hive.persistence.entity.Report;
import com.ga.hive.persistence.mapper.IReportsMapper;
import com.google.gson.Gson;

/**
 * The Class ReportsMapperImpl.
 *
 * @author Shalaka Nayal
 */

@Repository
public class ReportsMapperImpl implements IReportsMapper {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ReportsMapperImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.persistence.mapper.IReportsMapper#getReports(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<Report> getReports(String categoryName, String principleName, String startDate, String endDate)
            throws GAException {
        LOGGER.info("getReports ");
        /**
         * Check for the search Criteria first
         */
        String query = getQuery(categoryName, principleName, startDate, endDate);

        List<QuestionnaireDTO> answeredQuestionnaire = new ArrayList<QuestionnaireDTO>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            LOGGER.info("stmt :" + query);
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                AllotTask allotTask = new AllotTask();
                allotTask.setUserid(res.getString("userid"));
                String qa = res.getString("qa");

                Gson gson = new Gson();
                QuestionnaireDTO questionnaire = gson.fromJson(qa, QuestionnaireDTO.class);

                answeredQuestionnaire.add(questionnaire);
            }

            DbManager.closeConnection(connection);
            List<Report> reports = convertToReports(answeredQuestionnaire);
            return reports;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    /**
     * Convert to reports.
     *
     * @param answeredQuestionnaire the answered questionnaire
     * @return the list
     */
    private List<Report> convertToReports(List<QuestionnaireDTO> answeredQuestionnaire) {
        List<Report> reports = new ArrayList<Report>();
        Iterator<QuestionnaireDTO> iterator = answeredQuestionnaire.iterator();
        while (iterator.hasNext()) {
            QuestionnaireDTO qa = iterator.next();

            Report report = new Report();
            report.setTemplatename(qa.getTemplateName());
            report.setCategoryName(qa.getCategoryName());
            report.setPrincipleName(qa.getPrincipleName());
            report.setQaID(qa.getqID());
            report.setQaName(qa.getName());
            report.setRating(qa.getRating());
            reports.add(report);
        }
        return reports;
    }

    /**
     * Gets the query.
     *
     * @param categoryName the category name
     * @param principleName the principle name
     * @param startDate the start date
     * @param endDate the end date
     * @return the query
     */
    private String getQuery(String categoryName, String principleName, String startDate, String endDate) {

        String query = null;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            if (!categoryName.isEmpty() && principleName.isEmpty()) {
                query = "select * from reviewedTemplates where categoryName='" + categoryName + "' and creationtime>='"
                        + startDate + "' and creationtime<='" + endDate + "'";
            } else if (categoryName.isEmpty() && !principleName.isEmpty()) {
                query = "select * from reviewedTemplates where principleName='" + principleName
                        + "' and creationtime>='" + startDate + "' and creationtime<='" + endDate + "'";
            } else {
                query = "select * from reviewedTemplates creationtime>='" + startDate + "' and creationtime<='"
                        + endDate + "'";
            }
        } else if (startDate.isEmpty() && !endDate.isEmpty()) {
            if (!categoryName.isEmpty() && principleName.isEmpty()) {
                query = "select * from reviewedTemplates where categoryName='" + categoryName + " and creationtime<='"
                        + endDate + "'";
            } else if (categoryName.isEmpty() && !principleName.isEmpty()) {
                query = "select * from reviewedTemplates where principleName='" + principleName
                        + "' and creationtime<='" + endDate + "'";
            } else {
                query = "select * from reviewedTemplates creationtime<='" + endDate + "'";
            }
        } else if (!startDate.isEmpty() && endDate.isEmpty()) {
            if (!categoryName.isEmpty() && principleName.isEmpty()) {
                query = "select * from reviewedTemplates where categoryName='" + categoryName + "' and creationtime>='"
                        + startDate + "'";
            } else if (categoryName.isEmpty() && !principleName.isEmpty()) {
                query = "select * from reviewedTemplates where principleName='" + principleName
                        + "' and creationtime>='" + startDate + "'";
            } else {
                query = "select * from reviewedTemplates creationtime>='" + startDate + "'";
            }
        } else {
            if (!categoryName.isEmpty() && principleName.isEmpty()) {
                query = "select * from reviewedTemplates where categoryName='" + categoryName;
            } else if (categoryName.isEmpty() && !principleName.isEmpty()) {
                query = "select * from reviewedTemplates where principleName='" + principleName;
            } else {
                query = "select * from reviewedTemplates";
            }
        }

        return query;
    }

}
