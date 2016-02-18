package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Questionnaire;

public interface IQuestionnarieMapper {

    Boolean addQuestionnarie(Questionnaire questionnaire) throws GAException;

    List<Questionnaire> getAllQuestionnarie() throws GAException;

    Questionnaire getQuestionnarie(String questionnaireID) throws GAException;

    Boolean deleteQuestionnaireByID(String qid) throws GAException;

}
