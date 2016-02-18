package com.ga.hive.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.AnsQuestionnaire;
import com.ga.hive.persistence.entity.Reviews;
import com.ga.hive.service.IReviewsService;

/**
 * The Class ReviewsServiceImpl.
 *
 * @author Shalaka
 */
@Service
public class ReviewsServiceImpl implements IReviewsService {

    private static final Logger LOGGER = Logger.getLogger(ReviewsServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IReviewsService#getReviewByTemplateID(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Reviews getReviewByTemplateID(String templateID, String startRange, String endRange) throws GAException {
        LOGGER.info("getReviewByTemplateID ");
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = createQuery(templateID, startRange, endRange);
            ResultSet newRs = stmt.executeQuery(query);
            Map<Reviews, AnsQuestionnaire> quesMap = new HashMap<Reviews, AnsQuestionnaire>();
            List<AnsQuestionnaire> ansQuestionnaires = new ArrayList<AnsQuestionnaire>();
            Reviews reviews = new Reviews();
            while (newRs.next()) {

                String templatename = newRs.getString("templatename");
                String templateid = newRs.getString("templateid");
                String creationtime = newRs.getString("creationtime");
                String userid = newRs.getString("userid");

                reviews.setTemplateID(templateid);
                reviews.setTemplateName(templatename);
                reviews.setReviewDate(creationtime);
                reviews.setReviewerName(userid);

                String ansQues = newRs.getString("qa");
                LOGGER.info("" + ansQues);
                ObjectMapper mapper = new ObjectMapper();

                AnsQuestionnaire ansQuestionnaire = mapper.readValue(ansQues, AnsQuestionnaire.class);
                quesMap.put(reviews, ansQuestionnaire);

            }
            
            
            reviews.setAnsQuestionnaires(ansQuestionnaires);

            DbManager.closeConnection(connection);
            return reviews;

        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createQuery(String templateID, String startRange, String endRange) {
        String query = null;
        if ((startRange != null && !startRange.isEmpty()) && (endRange != null && !endRange.isEmpty())) {
            query = "select * from qatemplates where templateid=" + templateID + " and creationtime>=" + startRange
                    + " and creationtime<=" + endRange;
        } else if ((startRange != null && !startRange.isEmpty())) {
            query = "select * from qatemplates where templateid=" + templateID + " and creationtime>=" + startRange;
        } else if ((endRange != null && !endRange.isEmpty())) {
            query = "select * from qatemplates where templateid=" + templateID + " and creationtime<=" + endRange;
        } else {
            query = "select * from qatemplates where templateid=" + templateID;
        }

        return query;
    }
}
