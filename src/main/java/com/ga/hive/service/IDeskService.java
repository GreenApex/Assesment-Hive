package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.AnsTemplate;
import com.ga.hive.persistence.entity.TemplateDTO;

/**
 * The Interface IDeskService.
 *
 * @author Shalaka
 */
public interface IDeskService {

    Boolean saveTemplate(TemplateDTO templateDTO) throws GAException;

    TemplateDTO getTemplate(String tempID) throws GAException;

    List<TemplateDTO> getAllTemplates() throws GAException;

    Boolean saveSurveyResult(AnsTemplate saveSurveyResult) throws GAException;
}
