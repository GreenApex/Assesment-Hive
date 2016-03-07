package com.ga.hive.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.antlr.stringtemplate.language.Cat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.DbManager;
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.persistence.entity.AnsQuestionnaire;
import com.ga.hive.persistence.entity.AnsTemplate;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.persistence.entity.CategoryDTO;
import com.ga.hive.persistence.entity.Counter;
import com.ga.hive.persistence.entity.Principle;
import com.ga.hive.persistence.entity.PrincipleDTO;
import com.ga.hive.persistence.entity.Questionnaire;
import com.ga.hive.persistence.entity.TaskTemplate;
import com.ga.hive.persistence.entity.Team;
import com.ga.hive.persistence.entity.TemplateDTO;
import com.ga.hive.persistence.entity.User;
import com.ga.hive.service.ICategoryService;
import com.ga.hive.service.IDeskService;
import com.ga.hive.service.IPrincipalService;
import com.ga.hive.service.IQuestionnarieService;
import com.ga.hive.service.ITaskService;
import com.ga.hive.service.ITeamService;
import com.ga.hive.service.IUserService;
import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class DeskServiceImpl.
 *
 * @author Shalaka
 */
@Service
public class DeskServiceImpl implements IDeskService {

    private static final Logger LOGGER = Logger.getLogger(DeskServiceImpl.class);

    /** The task service. */
    @Autowired
    ITaskService taskService;

    /** The categories service. */
    @Autowired
    ICategoryService categoriesService;

    /** The princi service. */
    @Autowired
    IPrincipalService princiService;

    /** The quetionnaire service. */
    @Autowired
    IQuestionnarieService quetionnaireService;

    /** The team service. */
    @Autowired
    ITeamService teamService;

    /** The user service. */
    @Autowired
    IUserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IDeskService#saveTemplate(com.ga.hive.persistence.entity.TemplateDTO)
     */
    @Override
    public Boolean saveTemplate(TemplateDTO templateDTO) throws GAException {
        String fileName = null;
        try {
            if (templateDTO.getTemplateID() == null || templateDTO.getTemplateID().isEmpty()) {
                fileName = UUID.randomUUID().toString().replaceAll("-", "");
                templateDTO.setTemplateID(fileName);
            } else
                fileName = templateDTO.getTemplateID();

            boolean saved = saveData(templateDTO, fileName);

            if (!saved) {
                throw new GAException(ErrorCodes.GA_INTERNAL);
            } else {
                Connection connection = DbManager.getConnection();

                Statement stmt = connection.createStatement();
                String query = "load data local inpath '/home/local-sn/Desktop/" + fileName
                        + ".txt' overwrite into table surveytemplates PARTITION (templateid = '"
                        + templateDTO.getTemplateID() + "')";
                LOGGER.info(": " + query);
                stmt.execute(query);
                DbManager.closeConnection(connection);
            }
        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }
        return null;
    }

    /**
     * Save data.
     *
     * @param templateDTO the template dto
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean saveData(TemplateDTO templateDTO, String fileName) {
        FileOutputStream fop = null;
        File file;
        try {

            file = new File("/home/local-sn/Desktop/" + fileName + ".txt");
            fop = new FileOutputStream(file);
            // if file doesn't exists, then create it
            if (file.exists()) {
                file.createNewFile();
            }

            String template = null;

            template = templateDTO.getTemplateName() + "#";
            List<CategoryDTO> categoryDTOs = templateDTO.getCategoryList();
            Iterator<CategoryDTO> iterator = categoryDTOs.iterator();
            while (iterator.hasNext()) {

                CategoryDTO category = iterator.next();
                Gson gson = new Gson();
                String strCategory = gson.toJson(category);

                String categoryList = template + strCategory + "#" + templateDTO.getTemplateID();

                byte[] contentInBytes = categoryList.getBytes();

                fop.write(contentInBytes);
                fop.write("\n".getBytes());
            }

            // --------------------------------------------------------------
            fop.flush();
            fop.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IDeskService#getTemplate(java.lang.String)
     */
    @Override
    public TemplateDTO getTemplate(String tempID) throws GAException {
        LOGGER.info("getTemplate for " + tempID);
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM surveytemplates WHERE templateid='" + tempID + "'";
            LOGGER.info(": " + query);
            ResultSet rs = stmt.executeQuery(query);
            TemplateDTO templateDTO = new TemplateDTO();
            List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
            while (rs.next()) {
                String templateid = rs.getString("templateid");
                String templatename = rs.getString("templatename");

                templateDTO.setTemplateID(templateid);
                templateDTO.setTemplateName(templatename);

                String babycategory = rs.getString("CategoryDTO");
                LOGGER.info("" + babycategory);

                ObjectMapper mapper = new ObjectMapper();
                CategoryDTO categoryDTO = mapper.readValue(babycategory, CategoryDTO.class);
                categoryList.add(categoryDTO);
            }

            DbManager.closeConnection(connection);
            templateDTO.setCategoryList(categoryList);
            return templateDTO;

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

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IDeskService#saveSurveyResult(com.ga.hive.persistence.entity.AnsTemplate)
     */
    @Override
    public Boolean saveSurveyResult(AnsTemplate saveSurveyResult) throws GAException {

        try {
            Connection connection = DbManager.getConnection();
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO QATemplates PARTITION (templateid = '" + saveSurveyResult.getTemplateID()
                    + "', userid= '" + saveSurveyResult.getUserID() + "') VALUES ";
            List<AnsQuestionnaire> ansQuestionnaires = saveSurveyResult.getAnsQuestionnaire();
            Iterator<AnsQuestionnaire> iterator = ansQuestionnaires.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                index++;
                AnsQuestionnaire ansTemplate = iterator.next();
                Gson gson = new Gson();
                String ans = gson.toJson(ansTemplate);
                String creationtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                if (index <= 1) {
                    query = query + "('" + saveSurveyResult.getTemplateName() + "','" + ans + "', '" + creationtime
                            + "')";
                } else
                    query = query + ",('" + saveSurveyResult.getTemplateName() + "','" + ans + "','" + creationtime
                            + "')";
            }
            LOGGER.info(": " + query);
            boolean check = stmt.execute(query);
            DbManager.closeConnection(connection);
            return check;
        } catch (SQLException exception) {

            exception.printStackTrace();
            throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
        }

    }

    // @Override
    // public Boolean saveReviewedTemplates(AllotTask task) throws GAException {
    // try {
    // Connection connection = DbManager.getConnection();
    // Statement stmt = connection.createStatement();
    // TaskTemplate template = task.getTaskTemplate();
    // CategoryDTO categoryDTO = template.getCategory();
    // String query = "INSERT INTO reviewedTemplates VALUES ";
    //
    // LOGGER.info(": " + query);
    // List<PrincipleDTO> list = categoryDTO.getPrincipleList();
    // Iterator<PrincipleDTO> iterator = list.iterator();
    // int index = 0;
    // while (iterator.hasNext()) {
    // index++;
    // PrincipleDTO principle = iterator.next();
    //
    // /**
    // * Preparing QA column
    // */
    // Gson gson = new Gson();
    //
    // TaskTemplate reviewedTemplate = new TaskTemplate();
    // List<PrincipleDTO> rList = new ArrayList<PrincipleDTO>();
    // rList.add(principle);
    // CategoryDTO rCategory = new CategoryDTO();
    // rCategory.setCatrgoryID(template.getCategory().getCatrgoryID());
    // rCategory.setCatrgoryName(template.getCategory().getCatrgoryName());
    // rCategory.setPrincipleList(rList);
    // reviewedTemplate.setCategory(rCategory);
    // String qa = gson.toJson(reviewedTemplate);
    //
    // String creationtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    //
    // if (index <= 1) {
    // query = query + "('" + task.getUserid() + "','" + template.getTemplateName() + "',  '"
    // + categoryDTO.getCatrgoryName() + "', '" + principle.getPrincipleName() + "','" + qa
    // + "','" + creationtime + "')";
    // } else
    // query = query + ",( '" + task.getUserid() + "', '" + template.getTemplateName() + "', '"
    // + categoryDTO.getCatrgoryName() + "', '" + principle.getPrincipleName() + "','" + qa
    // + "','" + creationtime + "')";
    // }
    //
    // LOGGER.info(": " + query);
    // boolean check = stmt.execute(query);
    // DbManager.closeConnection(connection);
    // return check;
    // } catch (SQLException exception) {
    //
    // exception.printStackTrace();
    // throw new GAException(ErrorCodes.GA_DATABASE_GENERAL, exception);
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IDeskService#getAllTemplates()
     */
    @Override
    public List<TemplateDTO> getAllTemplates() throws GAException {
        LOGGER.info("getAllTemplates: ");
        try {
            Connection connection = DbManager.getConnection();

            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM surveytemplates";
            ResultSet newRs = stmt.executeQuery(query);
            List<TemplateDTO> ultimateDtos = new ArrayList<TemplateDTO>();

            Set<TemplateDTO> tempDtos = new HashSet<TemplateDTO>();

            Map<String, CategoryDTO> map = new HashMap<String, CategoryDTO>();

            int index = 0;
            while (newRs.next()) {
                index++;
                TemplateDTO templateDTO = new TemplateDTO();
                String templateid = newRs.getString("templateid");
                String templatename = newRs.getString("templatename");
                String creationtime = newRs.getString("creationtime");

                templateDTO.setTemplateID(templateid);
                templateDTO.setTemplateName(templatename);
                templateDTO.setCreationTime(creationtime);
                tempDtos.add(templateDTO);

                String babycategory = newRs.getString("CategoryDTO");
                LOGGER.info("" + babycategory);
                ObjectMapper mapper = new ObjectMapper();
                CategoryDTO categoryDTO = mapper.readValue(babycategory, CategoryDTO.class);
                map.put(templateid + "-" + index, categoryDTO);
            }

            LOGGER.info("total Templates: " + tempDtos);
            Iterator<TemplateDTO> iterator = tempDtos.iterator();
            while (iterator.hasNext()) {

                TemplateDTO templateDTO = iterator.next();
                List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
                Iterator<Entry<String, CategoryDTO>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    @SuppressWarnings("unchecked")
                    Map.Entry<String, CategoryDTO> pair = (Map.Entry<String, CategoryDTO>) it.next();
                    String[] tempID = pair.getKey().split("-");
                    System.out.println(tempID[0] + " = " + pair.getValue());

                    if (templateDTO.getTemplateID().equals(tempID[0])) {
                        categoryList.add(pair.getValue());
                    }
                }
                if (!categoryList.isEmpty())
                    templateDTO.setCategoryList(categoryList);
                if (templateDTO.getCategoryList() != null) {
                    ultimateDtos.add(templateDTO);
                }
            }
            DbManager.closeConnection(connection);
            return ultimateDtos;

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

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IDeskService#getDeskMembersCounts(java.lang.String)
     */
    @Override
    public Counter getDeskMembersCounts(String userID) throws GAException {
        Counter counter = new Counter();
        List<TemplateDTO> templateDTOs = getAllTemplates();
        counter.setCategoriesCount(templateDTOs.size());

        AllotTask allotTask = taskService.getMyTasks(userID);
        counter.setTasksCount((allotTask.getCategoryList().size() + allotTask.getPrincipleList().size()));

        List<Category> categories = categoriesService.getAllCategory();
        counter.setCategoriesCount(categories.size());

        List<Principle> principles = princiService.getAllPrincipal();
        counter.setPrinciplesCount(principles.size());

        List<Questionnaire> questionnaires = quetionnaireService.getAllQuestionnarie();
        counter.setQuestionnaireCount(questionnaires.size());

        List<Team> teams = teamService.getAllteam();
        counter.setTeamsCount(teams.size());

        List<User> users = userService.getActiveUsers();
        counter.setUsersCount(users.size());
        return counter;
    }

}
