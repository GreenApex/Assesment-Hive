package com.ga.hive.domain.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Questionnaire;
import com.ga.hive.service.IQuestionnarieService;

/**
 * The Class QuestionnaireController.
 *
 * @author Shalaka Nayal
 */
@RestController
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(QuestionnaireController.class);

    /** The qustionnariservice. */
    @Autowired
    IQuestionnarieService qustionnariservice;

    /**
     * Adds the questionnarie.
     *
     * @param questionnaire the questionnaire
     * @return the string
     * @throws GAException the GA exception
     */
    @RequestMapping(value = "/addquestionnaire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addQuestionnarie(@RequestBody Questionnaire questionnaire) throws GAException {
        LOGGER.info("addQuestionnarie controller");
        Boolean result = qustionnariservice.addQuestionnarie(questionnaire);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, result);
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);
    }

    /**
     * Gets the all questionnarie.
     *
     * @return the all questionnarie
     */
    @RequestMapping(value = "/getallquestionnarie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllQuestionnarie() {
        LOGGER.info("getAllQuestionnarie");
        try {
            List<Questionnaire> questionnaireList = qustionnariservice.getAllQuestionnarie();
            if (questionnaireList != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, questionnaireList);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "No Questionnarie called.");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    /**
     * Gets the questionnarie by id.
     *
     * @param questionnaireID the questionnaire id
     * @return the questionnarie by id
     * @throws GAException the GA exception
     */
    @RequestMapping(value = "/getquestionnarieid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getQuestionnarieByID(@RequestParam("questionnaireID") String questionnaireID)
            throws GAException {
        LOGGER.info("get categoryByid controller");
        try {
            Questionnaire questionnaire = qustionnariservice.getQuestionnarieByID(questionnaireID);
            if (questionnaire != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, questionnaire);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, "No Questionnarie found");
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    /**
     * Delete questionnaire.
     *
     * @param qid the qid
     * @return the string
     * @throws GAException the GA exception
     */
    @RequestMapping(value = "/deletequestionnaire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteQuestionnaire(@RequestParam("qid") String qid) throws GAException {
        LOGGER.info("deleteQuestionnaire controller");
        Boolean result = qustionnariservice.deleteQuestionnaireByID(qid);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "principle deleted successfully");
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }
}
