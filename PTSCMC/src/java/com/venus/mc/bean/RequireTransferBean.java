/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class RequireTransferBean {

    private int rtId;
    private String createdDate;
    private int createdEmpId;
    private int orgId;
    private String orgName;
    private String rtNumber;
    private String reason;

    public RequireTransferBean() {
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

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setRtNumber(String rtNumber) {
        this.rtNumber = rtNumber;
    }

    public String getRtNumber() {
        return rtNumber;
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
