package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Questionnaire;

public interface IQuestionnarieService {

    Boolean addQuestionnarie(Questionnaire questionnaire) throws GAException;

    List<Questionnaire> getAllQuestionnarie() throws GAException;

    Questionnaire getQuestionnarieByID(String questionnaireID) throws GAException;

    Boolean deleteQuestionnaireByID(String qid) throws GAException;

}
