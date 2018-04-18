/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.test;

import com.venus.core.sax.HierReportBean;
import com.venus.mc.core.HierSpineReportParser;
import java.util.ArrayList;

/**
 *
 * @author PhuongTu
 */
public class testReportBean extends HierReportBean {

    private ArrayList beanLevel1;

    public testReportBean() {
    }

    public testReportBean(HierSpineReportParser report) {
        super(report);
    }

    public ArrayList getBeanLevel1() {
        if (beanLevel1 == null) {
            beanLevel1 = new ArrayList();
        }
        return beanLevel1;
    }

    public void setBeanLevel1(ArrayList beanLevel1) {
        this.beanLevel1 = beanLevel1;
    }
}
