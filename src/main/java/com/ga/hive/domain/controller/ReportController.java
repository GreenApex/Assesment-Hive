package com.ga.hive.domain.controller;

import java.util.List;

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
import com.ga.hive.persistence.entity.Report;
import com.ga.hive.service.IReportService;

/**
 * The Class ReportController.
 *
 * @author Shalaka Nayal
 */
@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    private static final Logger LOGGER = Logger.getLogger(ReportController.class);

    @Autowired
    IReportService reportService;

    /**
     * Gets the report.
     *
     * @param categoryName the category name
     * @param principleName the principle name
     * @param startDate the start date
     * @param endDate the end date
     * @return the report
     */
    @RequestMapping(value = "/getReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getReport(@RequestParam("categoryName") String categoryName,
            @RequestParam("principleName") String principleName, @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LOGGER.info("getReviewByTemplated");
        try {

            List<Report> reports = reportService.getReports(categoryName, principleName, startDate, endDate);
            if (reports.size() <= 0)
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, null);
            else
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, reports);
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }
    }
}
