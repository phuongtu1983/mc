/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.sax;

import com.venus.mc.core.HierSpineReportParser;

/**
 *
 * @author PhuongTu
 */
public class HierReportBean {

    private HierSpineReportParser report;

    public HierReportBean() {

    }

    public HierReportBean(HierSpineReportParser report) {
        this.report = report;
    }

    public HierSpineReportParser getReport() {
        return report;
    }

    public void setReport(HierSpineReportParser report) {
        this.report = report;
    }

    public void generate(Object obj) {
        HierTableBean tableBean = (HierTableBean) obj;
        tableBean.generate(report);
    }
}
