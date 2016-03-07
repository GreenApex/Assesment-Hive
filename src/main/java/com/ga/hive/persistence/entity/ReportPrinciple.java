package com.ga.hive.persistence.entity;

import java.util.List;

public class ReportPrinciple {

    public String principleID;
    public String principleName;
    public List<ReportQA> reportQA;

    public String getPrincipleID() {
        return principleID;
    }

    public void setPrincipleID(String principleID) {
        this.principleID = principleID;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public List<ReportQA> getReportQA() {
        return reportQA;
    }

    public void setReportQA(List<ReportQA> reportQA) {
        this.reportQA = reportQA;
    }

    @Override
    public String toString() {
        return "ReportPrinciple [principleID=" + principleID + ", principleName=" + principleName + ", reportQA="
                + reportQA + "]";
    }

}
