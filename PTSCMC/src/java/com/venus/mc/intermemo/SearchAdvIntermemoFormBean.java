/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.intermemo;

/**
 *
 * @author phuongtu
 */
public class SearchAdvIntermemoFormBean extends org.apache.struts.action.ActionForm {

    private String requestNumber;
    private String fromDate;
    private String toDate;
    private int createdEmp;
    private String subject;
    private int statusSuggest;

    //constructure region
    public SearchAdvIntermemoFormBean() {
        super();
    // TODO Auto-generated constructor stub
    }

    public String getRequestNumber() {
        return this.requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getStatusSuggest() {
        return this.statusSuggest;
    }

    public void setStatusSuggest(int statusSuggest) {
        this.statusSuggest = statusSuggest;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
