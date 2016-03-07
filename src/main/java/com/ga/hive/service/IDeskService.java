package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Counter;
import com.ga.hive.persistence.entity.QuestionnaireDTO;
import com.ga.hive.persistence.entity.TemplateDTO;

/**
 * The Interface IDeskService.
 *
 * @author Shalaka
 */
public interface IDeskService {

    /**
     * Save template.
     *
     * @param templateDTO the template dto
     * @return the boolean
     * @throws GAException the GA exception
     */
    Boolean saveTemplate(TemplateDTO templateDTO) throws GAException;

    /**
     * Gets the template.
     *
     * @param tempID the temp id
     * @return the template
     * @throws GAException the GA exception
     */
    TemplateDTO getTemplate(String tempID) throws GAException;

    /**
     * Gets the all templates.
     *
     * @return the all templates
     * @throws GAException the GA exception
     */
    List<TemplateDTO> getAllTemplates() throws GAException;

    /**
     * Gets the desk members counts.
     *
     * @param userID the user id
     * @return the desk members counts
     * @throws GAException the GA exception
     */
    Counter getDeskMembersCounts(String userID) throws GAException;

    /**
     * Save reviewed templates.
     *
     * @param list the list
     * @return the boolean
     */
    Boolean saveReviewedTemplates(List<QuestionnaireDTO> list, String userid) throws GAException;
}
