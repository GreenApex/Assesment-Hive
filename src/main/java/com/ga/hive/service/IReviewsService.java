package com.ga.hive.service;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Reviews;

/**
 * The Interface IReviewsService.
 * 
 * @author Shalaka
 *
 */
public interface IReviewsService {

    /**
     * Gets the review by template id.
     *
     * @param templateID the template id
     * @param startRange the start range
     * @param endRange the end range
     * @return the review by template id
     * @throws GAException the GA exception
     */
    Reviews getReviewByTemplateID(String templateID, String startRange, String endRange) throws GAException;

}
