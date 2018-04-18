/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.mc.core.SpineReportParser;
import org.jdom.Element;

/**
 *
 * @author phuongtu
 */
public class TCReport extends SpineReportParser {

    public TCReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Element eLeterhead = root.getChild("mcrp_leterhead");
        eLeterhead.setText("HELLO EVERY BODY");
        Element eClientName = root.getChild("mcrp_clientname");
        eClientName.setText("My name is Nguyen Phuong Tu");
    }
}