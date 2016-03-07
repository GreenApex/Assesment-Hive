package com.ga.hive.service;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Report;

/**
 * The Interface IReportService.
 *
 * @author Shalaka Nayal
 */
public interface IReportService {

    /**
     * Gets the reports.
     *
     * @param categoryName the category name
     * @param principleName the principle name
     * @param startDate the start date
     * @param endDate the end date
     * @return the reports
     * @throws GAException the GA exception
     */
    List<Report> getReports(String categoryName, String principleName, String startDate, String endDate)
            throws GAException;

}
