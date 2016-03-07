package com.ga.hive.persistence.mapper;

import java.util.List;

import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Report;

public interface IReportsMapper {

    List<Report> getReports(String categoryName, String principleName, String startDate, String endDate)
            throws GAException;

}
