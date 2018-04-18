/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.evaluate;

import com.venus.mc.bean.VendorEvaluateBean;

/**
 *
 * @author phuongtu
 */
public class VendorEvalFormBean extends org.apache.struts.action.ActionForm {

    private int evalId;
    private int empId;
    private int orgId;
    private int venId;
    private String createdDate;
    private String fromDate;
    private String toDate;
    private int lastResult;
    private String orgName;
    private String venName;
    private String[] detIds;
    private int[] detResults;
    private String[] detNotes;
    private String evalNumber;

    public VendorEvalFormBean() {
        super();
    }

    public VendorEvalFormBean(VendorEvaluateBean bean) {
        this.evalId = bean.getEvalId();
        this.empId = bean.getEmpId();
        this.orgId = bean.getOrgId();
        this.venId = bean.getVenId();
        this.createdDate = bean.getCreatedDate();
        this.fromDate = bean.getFromDate();
        this.toDate = bean.getToDate();
        this.lastResult = bean.getLastResult();
        this.evalNumber = bean.getEvalNumber();
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getEmpId() {
        return empId;
    }

    public int getEvalId() {
        return evalId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public int getLastResult() {
        return lastResult;
    }

    public int getOrgId() {
        return orgId;
    }

    public String getToDate() {
        return toDate;
    }

    public int getVenId() {
        return venId;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setEvalId(int evalId) {
        this.evalId = evalId;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public void setDetIds(String[] detIds) {
        this.detIds = detIds;
    }

    public String[] getDetIds() {
        return detIds;
    }

    public void setDetNotes(String[] detNotes) {
        this.detNotes = detNotes;
    }

    public String[] getDetNotes() {
        return detNotes;
    }

    public void setDetResults(int[] detResults) {
        this.detResults = detResults;
    }

    public int[] getDetResults() {
        return detResults;
    }

    public String getEvalNumber() {
        return evalNumber;
    }

    public void setEvalNumber(String evalNumber) {
        this.evalNumber = evalNumber;
    }
}
