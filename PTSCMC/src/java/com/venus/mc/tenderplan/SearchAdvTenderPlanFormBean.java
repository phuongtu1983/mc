/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

/**
 *
 * @author phuongtu
 */
public class SearchAdvTenderPlanFormBean extends org.apache.struts.action.ActionForm {

    private String tenderNumber;
    private String fromDate;
    private String toDate;
    private String packname;
    private int form;

    //constructure region
    public SearchAdvTenderPlanFormBean() {
        super();
    // TODO Auto-generated constructor stub
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }
}
