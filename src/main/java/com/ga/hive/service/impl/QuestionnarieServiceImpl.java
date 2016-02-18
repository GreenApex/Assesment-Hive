package com.ga.hive.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Questionnaire;
import com.ga.hive.persistence.mapper.IQuestionnarieMapper;
import com.ga.hive.service.IQuestionnarieService;

@Service
public class QuestionnarieServiceImpl implements IQuestionnarieService {

    private static final Logger LOGGER = Logger.getLogger(QuestionnarieServiceImpl.class);

    @Autowired
    IQuestionnarieMapper questionnariemapper;

    @Override
    public Boolean addQuestionnarie(Questionnaire questionnaire) throws GAException {
        LOGGER.info("createQuestionnarie: " + questionnaire);
        if (questionnaire.getQuestionnaireID() == null)
            questionnaire.setQuestionnaireID(UUID.randomUUID().toString().replaceAll("-", ""));

        questionnaire.setIsdelete(false);
        return questionnariemapper.addQuestionnarie(questionnaire);
    }

    @Override
    public List<Questionnaire> getAllQuestionnarie() throws GAException {
        List<Questionnaire> questionnaireList = questionnariemapper.getAllQuestionnarie();
        LOGGER.info("categoryList :" + questionnaireList);
        return questionnaireList;
    }

    @Override
    public Questionnaire getQuestionnarieByID(String questionnaireID) throws GAException {
        Questionnaire dbQuestionnarie = questionnariemapper.getQuestionnarie(questionnaireID);
        LOGGER.info("dbQuestionnarie :" + dbQuestionnarie);
        return dbQuestionnarie;
    }

    @Override
    public Boolean deleteQuestionnaireByID(String qid) throws GAException {
        LOGGER.info("deleteCategoryByID :");
        Questionnaire dbQuestionnaire = getQuestionnarieByID(qid);
        if (dbQuestionnaire == null) {
            throw new GAException(ErrorCodes.GA_DATA_NOT_FOUND, "No Questionnaire found");
        } else {
            return questionnariemapper.deleteQuestionnaireByID(qid);
        }
    }
}
