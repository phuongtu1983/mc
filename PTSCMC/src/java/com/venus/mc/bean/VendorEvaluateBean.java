/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class VendorEvaluateBean {

    private int evalId;
    private int empId;
    private int orgId;
    private int venId;
    private String createdDate;
    private String fromDate;
    private String toDate;
    private int lastResult;
    private String orgName;
    private String resultString;
    private String evalNumber;

    public VendorEvaluateBean() {
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

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getEvalNumber() {
        return evalNumber;
    }

    public void setEvalNumber(String evalNumber) {
        this.evalNumber = evalNumber;
    }
}
