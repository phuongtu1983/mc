/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReportBean;

import com.venus.core.sax.HierReportBean;
import com.venus.mc.core.HierSpineReportParser;
import java.util.ArrayList;

/**
 *
 * @author PhuongTu
 */
public class BidEvalSumReportBean extends HierReportBean {

    private ArrayList arrBeanVendor;

    public BidEvalSumReportBean() {
    }

    public BidEvalSumReportBean(HierSpineReportParser report) {
        super(report);
    }

    public ArrayList getArrBeanVendor() {
        if (arrBeanVendor == null) {
            arrBeanVendor = new ArrayList();
        }
        return arrBeanVendor;
    }

    public void setArrBeanVendor(ArrayList arrBeanVendor) {
        this.arrBeanVendor = arrBeanVendor;
    }

    public void addBeanVendor(BidEvalSumReportVendorBean vendor) {
        if (this.arrBeanVendor == null) {
            this.arrBeanVendor = new ArrayList();
        }
        this.arrBeanVendor.add(vendor);
    }
}
