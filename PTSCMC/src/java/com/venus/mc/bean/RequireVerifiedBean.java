/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class RequireVerifiedBean {

    private int rvId;
    private String createdDate;
    private int createdEmpId;
    private int orgId;
    private String orgName;
    private String rvNumber;
    private String reason;

    public RequireVerifiedBean() {
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setRvId(int rvId) {
        this.rvId = rvId;
    }

    public int getRvId() {
        return rvId;
    }

    public void setRvNumber(String rvNumber) {
        this.rvNumber = rvNumber;
    }

    public String getRvNumber() {
        return rvNumber;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }
}
