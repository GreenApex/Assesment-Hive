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
import com.ga.hive.persistence.entity.AllotTask;
import com.ga.hive.persistence.entity.Counter;
import com.ga.hive.persistence.entity.TemplateDTO;
import com.ga.hive.service.IDeskService;

/**
 * The Class DeskController.
 *
 * @author Shalaka
 */
@RestController
@RequestMapping(value = "/desk")
public class DeskController {

    private static final Logger LOGGER = Logger.getLogger(DeskController.class);

    @Autowired
    IDeskService deskService;

    @RequestMapping(value = "/createTemplate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createTemplate(@RequestBody TemplateDTO templateDTO) {
        LOGGER.info("createTemplate controller");
        try {

            Boolean check = deskService.saveTemplate(templateDTO);

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, check);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/gettemplate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getTemplate(@RequestParam("templateID") String templateID) {
        LOGGER.info("getTemplate controller");
        try {

            TemplateDTO dto = deskService.getTemplate(templateID);

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, dto);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

    /*
     * @RequestMapping(value = "/saveSurveyResult", method = RequestMethod.POST, consumes =
     * MediaType.APPLICATION_JSON_VALUE) public @ResponseBody String saveSurveyResult(@RequestBody AnsTemplate
     * saveSurveyResult) { LOGGER.info("saveSurveyResult controller"); try {
     * 
     * Boolean dto = deskService.saveSurveyResult(saveSurveyResult);
     * 
     * return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, dto); } catch (GAException e) { LOGGER.error(" " + e);
     * return JsonUtility.getJson(e.getCode(), e.getDescription()); }
     * 
     * }
     */
//    @RequestMapping(value = "/saveSurveyResult", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody String saveSurveyResult(@RequestBody AllotTask reviewedTask) {
//        LOGGER.info("saveSurveyResult controller");
//        try {
//
//            Boolean check = deskService.saveReviewedTemplates(reviewedTask);
//
//            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, check);
//        } catch (GAException e) {
//            LOGGER.error(" " + e);
//            return JsonUtility.getJson(e.getCode(), e.getDescription());
//        }
//
//    }

    @RequestMapping(value = "/getAllTemplates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllTemplates() {
        LOGGER.info("getAllTemplates controller");
        try {

            List<TemplateDTO> templateDTOs = deskService.getAllTemplates();

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, templateDTOs);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getCounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getCounts(@RequestParam("userID") String userID) {
        LOGGER.info("getCounts Controller");
        try {

            Counter counter = deskService.getDeskMembersCounts(userID);
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, counter);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }
}
