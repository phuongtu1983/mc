/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class HandedReportBean {

    private int hrId;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private int orgId;
    private String orgName;
    private int empReceiveId;
    private String empReceiveName;
    private int orgReceiveId;
    private String orgReceiveName;
    private String hrNumber;
    private String createdTime;
    private String createdLocation;
    private int rtId;

    public HandedReportBean() {
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

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setCreatedLocation(String createdLocation) {
        this.createdLocation = createdLocation;
    }

    public String getCreatedLocation() {
        return createdLocation;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrNumber(String hrNumber) {
        this.hrNumber = hrNumber;
    }

    public String getHrNumber() {
        return hrNumber;
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

    public void setOrgReceiveId(int orgReceiveId) {
        this.orgReceiveId = orgReceiveId;
    }

    public void setOrgReceiveName(String orgReceiveName) {
        this.orgReceiveName = orgReceiveName;
    }

    public int getOrgReceiveId() {
        return orgReceiveId;
    }

    public String getOrgReceiveName() {
        return orgReceiveName;
    }

    public void setEmpReceiveId(int empReceiveId) {
        this.empReceiveId = empReceiveId;
    }

    public int getEmpReceiveId() {
        return empReceiveId;
    }

    public void setEmpReceiveName(String empReceiveName) {
        this.empReceiveName = empReceiveName;
    }

    public String getEmpReceiveName() {
        return empReceiveName;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }
}
