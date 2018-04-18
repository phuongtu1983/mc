/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.request;

/**
 *
 * @author phuongtu
 */
public class SearchAdvRequestFormBean extends org.apache.struts.action.ActionForm {

    private String requestNumber;
    private String fromDate;
    private String toDate;
    private int createdEmp;
    private int createdOrg;
    private int whichUse;
    private int proId;
    private int orgId;
    private String[] approveSuggest;
    private int statusSuggest;

    //constructure region
    public SearchAdvRequestFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getRequestNumber() {
        return this.requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String[] getApproveSuggest() {
        return approveSuggest;
    }

    public void setApproveSuggest(String[] approveSuggest) {
        this.approveSuggest = approveSuggest;
    }

    public int getStatusSuggest() {
        return this.statusSuggest;
    }

    public void setStatusSuggest(int statusSuggest) {
        this.statusSuggest = statusSuggest;
    }

    public int getWhichUse() {
        return this.whichUse;
    }

    public void setWhichUse(int whichUse) {
        this.whichUse = whichUse;
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

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }
}
