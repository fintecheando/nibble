/*
 *
 *
 */
package org.nibble.util;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * This is a Wrapper class from Jasper Reports
 * 
 */
public class ReportTemplate {
    /** Headers from Report */
    private String[] headers;

    /** Columns from DataSource */
    private Object[][] data;

    /** DataSource Name */
    private String dsName;

    /** Parameter used form Report */
    private HashMap params;

    /** Report Path file */
    private String reportName;

    /** SubReport Path file */
    private String subReportName;

    /** Tyoe of Generate report PDF, PRN refer to ReportKeys */
    private int reportType;

    /** Tyoe of Generate report PDF, PRN refer to ReportKeys */
    private ArrayList subReports;

    /**
     * Defualt contructor, not subreports
     */
    public ReportTemplate() {
    }

    /**
     * Get Data
     *
     * @return Object[][]
     */
    public Object[][] getData() {
        return data;
    }

    /**
     * get Headers to report
     *
     * @return String
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * Get parameters to report
     *
     * @return HashMap
     */
    public HashMap getParams() {
        return params;
    }

    /**
     * Sets the data.
     *
     * @param data The data to set
     */
    public void setData(Object[][] data) {
        this.data = data;
    }

    /**
     * Sets the data and name form datasource is util form reports.
     *
     * @param newDsName DOCUMENT ME!
     * @param data The data to set
     */
    public void setData(String newDsName, Object[][] data) {
        this.data = data;
        this.dsName = newDsName;
    }

    /**
     * Sets the headers.
     *
     * @param headers The headers to set
     */
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    /**
     * Sets the params.
     *
     * @param params The params to set
     */
    public void setParams(HashMap params) {
        this.params = params;
    }

    /**
     * Sets the params.
     *
     * @param keyParam Paramkey
     * @param param Param
     */
    public void addParam(Object keyParam, Object param) {
        if (this.params == null) {
            params = new HashMap();
        }

        this.params.put(keyParam, param);
    }

    /**
     * Gets ReportName
     *
     * @return String
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the reportName.
     *
     * @param reportName The reportName to set
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * Return type of report
     *
     * @return int
     */
    public int getReportType() {
        return reportType;
    }

    /**
     * Sets the reportType.
     *
     * @param reportType The reportType to set
     */
    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    /**
     * Get SubReport
     *
     * @param indexSubReport Index of subreport
     *
     * @return ReportTemplate[]
     */
    public ReportTemplate getSubReport(int indexSubReport) {
        ReportTemplate result = null;

        if (subReports != null) {
            result = (ReportTemplate) subReports.get(indexSubReport);
        }

        return result;
    }

    /**
     * Sets the subReport.
     *
     * @param newSubReport The subReport to set
     */
    public void setSubReport(ReportTemplate newSubReport) {
        if (subReports == null) {
            subReports = new ArrayList();
        }

        subReports.add(newSubReport);
    }

    /**
     * Get Number of subreports permitted for this report
     *
     * @return int
     */
    public int getSubReportsSize() {
        if (subReports != null) {
            return subReports.size();
        } else {
            return 0;
        }
    }

    /**
     * Gets SubReport Name
     *
     * @return String
     */
    public String getSubReportName() {
        return subReportName;
    }

    /**
     * Sets the subReportName.
     *
     * @param subReportName The subReportName to set
     */
    public void setSubReportName(String subReportName) {
        this.subReportName = subReportName;
    }

    /**
     * DOCUMENT ME!
     *
     * @return String
     */
    public String getDsName() {
        return dsName;
    }

    /**
     * Sets the dsName.
     *
     * @param dsName The dsName to set
     */
    public void setDsName(String dsName) {
        this.dsName = dsName;
    }
}
