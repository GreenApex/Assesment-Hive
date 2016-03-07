package com.ga.hive.domain.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Reviews;
import com.ga.hive.service.IReviewsService;

/**
 * The Class ReviewsController.
 *
 * @author Shalaka
 */
@RestController
@RequestMapping(value = "/reviews")
public class ReviewsController {

    private static final Logger LOGGER = Logger.getLogger(ReviewsController.class);

    @Autowired
    IReviewsService reviewsService;

    /**
     * Gets the review by templateId.
     *
     * @param templateID the template id
     * @param startRange the start range
     * @param endRange the end range
     * @return the review by templated
     */
    @RequestMapping(value = "/ReviewByTemplateId", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getReviewByTemplateId(@RequestParam("templateID") String templateID,
            @RequestParam("startRange") String startRange, @RequestParam("endRange") String endRange) {
        LOGGER.info("getReviewByTemplated");
        try {

            Reviews reviews = reviewsService.getReviewByTemplateID(templateID, startRange, endRange);

            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, reviews);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }

}
