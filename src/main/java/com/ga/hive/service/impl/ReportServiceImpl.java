package com.ga.hive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Report;
import com.ga.hive.persistence.mapper.IReportsMapper;
import com.ga.hive.service.IReportService;

/**
 * The Class ReportServiceImpl.
 *
 * @author Shalaka Nayal
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    IReportsMapper reportMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.hive.service.IReportService#getReports(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Report> getReports(String categoryName, String principleName, String startDate, String endDate)
            throws GAException {

        return reportMapper.getReports(categoryName, principleName, startDate, endDate);

    }

}
