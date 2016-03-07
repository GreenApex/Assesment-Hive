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
import com.ga.hive.persistence.entity.CategoryDTO;
import com.ga.hive.persistence.entity.PrincipleDTO;
import com.ga.hive.persistence.entity.QuestionnaireDTO;
import com.ga.hive.persistence.entity.Report;
import com.ga.hive.persistence.entity.ReportCategory;
import com.ga.hive.persistence.entity.ReportPrinciple;
import com.ga.hive.persistence.entity.ReportQA;
import com.ga.hive.persistence.entity.TaskTemplate;
import com.ga.hive.persistence.mapper.IReportsMapper;
import com.google.gson.Gson;

@Repository
public class ReportsMapperImpl implements IReportsMapper {
    private static final Logger LOGGER = Logger.getLogger(ReportsMapperImpl.class);

    @Override
    public List<Report> getReports(String categoryName, String principleName, String startDate, String endDate)
            throws GAException {
        LOGGER.info("getReports ");
        /**
         * Check for the search Criteria first
         */
        String query = getQuery(categoryName, principleName, startDate, endDate);

        List<AllotTask> tasks = new ArrayList<AllotTask>();
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
                TaskTemplate task = gson.fromJson(qa, TaskTemplate.class);
//                allotTask.setTaskTemplate(task);

                tasks.add(allotTask);
            }

            DbManager.closeConnection(connection);
            List<Report> reports = convertToReports(tasks);
            return reports;
        } catch (SQLException exception) {
            exception.printStackTrace();
            LOGGER.error("Internal failure");
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
    }

    private List<Report> convertToReports(List<AllotTask> tasks) {
        List<Report> reports = new ArrayList<Report>();
        Iterator<AllotTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            AllotTask task = iterator.next();

            Report report = new Report();

            report.setUserID(task.getUserid());
//            TaskTemplate template = task.getTaskTemplate();
//            report.setTemplatedID(template.getTemplateID());
//            report.setTemplatename(template.getTemplateName());

            ReportCategory reportCategory = new ReportCategory();
//            CategoryDTO categoryDTO = template.getCategory();
//            reportCategory.setCategoryID(categoryDTO.getCatrgoryID());
//            reportCategory.setCategoryName(categoryDTO.getCatrgoryName());

//            PrincipleDTO principle = categoryDTO.getPrincipleList().get(0);
            ReportPrinciple reportPrinciple = new ReportPrinciple();
//            reportPrinciple.setPrincipleID(principle.getPrincipleID());
//            reportPrinciple.setPrincipleName(principle.getPrincipleName());

//            List<QuestionnaireDTO> originalList = principle.getQuestionnaireList();
//            Iterator<QuestionnaireDTO> iterator2 = originalList.iterator();
//            List<ReportQA> reportQAList = new ArrayList<ReportQA>();
//            while (iterator2.hasNext()) {
//                QuestionnaireDTO question = iterator2.next();
//                ReportQA reportQA = new ReportQA();
//                reportQA.setQaID(question.getqID());
//                reportQA.setQaName(question.getName());
//                reportQA.setRating(question.getRating());
//                reportQAList.add(reportQA);
//            }
//            reportPrinciple.setReportQA(reportQAList);
//            reportCategory.setReportPrinciple(reportPrinciple);
//            report.setCategory(reportCategory);
//
//            reports.add(report);
        }
        return reports;
    }

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
