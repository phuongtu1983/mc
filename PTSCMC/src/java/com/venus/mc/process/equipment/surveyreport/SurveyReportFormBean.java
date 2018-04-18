/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.surveyreport;

import com.venus.mc.bean.SurveyReportBean;

/**
 *
 * @author mai vinh loc
 */
public class SurveyReportFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int srId;
    private int orgId;
    private String createdDate;
    private String surveyDate;
    private int createdEmp;
    private String srNumber;
    private String createdTime;
    private String createdLocation;
    private int managerEmp;
    private int managerEquipmentEmp;
    private String reasonToRepair;
    private int request;
    private String comment;
    private String usedOrg;
    private String[] reqDetId;
    private String[] reqMatId;
    private String[] matId;
    private String[] equId;
    private String[] unit;
    private String[] usedCode;
    private String[] quantity;
    private String[] qt;

    //constructure region
    public SurveyReportFormBean() {
        super();

    }

    public SurveyReportFormBean(SurveyReportBean bean) {
        this.srId = bean.getSrId();
        this.createdEmp = bean.getCreatedEmp();
        this.createdDate = bean.getCreatedDate();
        this.surveyDate = bean.getSurveyDate();
        this.srNumber = bean.getSrNumber();
        this.comment = bean.getComment();
        this.createdTime = bean.getCreatedTime();
        this.createdLocation = bean.getCreatedLocation();
        this.managerEmp = bean.getManagerEmp();
        this.managerEquipmentEmp = bean.getManagerEquipmentEmp();
        this.reasonToRepair = bean.getReasonToRepair();
        this.request = bean.getRequest();
        this.usedOrg = bean.getUsedOrg();
        this.orgId = bean.getOrgId();

    }

    public String getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(String usedOrg) {
        this.usedOrg = usedOrg;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public String getCreatedLocation() {
        return createdLocation;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getManagerEmp() {
        return managerEmp;
    }

    public int getManagerEquipmentEmp() {
        return managerEquipmentEmp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public void setCreatedLocation(String createdLocation) {
        this.createdLocation = createdLocation;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setManagerEmp(int managerEmp) {
        this.managerEmp = managerEmp;
    }

    public void setManagerEquipmentEmp(int managerEquipmentEmp) {
        this.managerEquipmentEmp = managerEquipmentEmp;
    }

    public String getReasonToRepair() {
        return reasonToRepair;
    }

    public void setReasonToRepair(String reasonToRepair) {
        this.reasonToRepair = reasonToRepair;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getSrId() {
        return srId;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public String getSrNumber() {
        return srNumber;
    }

    public void setSrNumber(String srNumber) {
        this.srNumber = srNumber;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String[] getEquId() {
        return equId;
    }

    public String[] getMatId() {
        return matId;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public String[] getUnit() {
        return unit;
    }

    public String[] getUsedCode() {
        return usedCode;
    }

    public void setEquId(String[] equId) {
        this.equId = equId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public void setUsedCode(String[] usedCode) {
        this.usedCode = usedCode;
    }

    public void setQt(String[] qt) {
        this.qt = qt;
    }

    public String[] getQt() {
        return qt;
    }

    public String[] getReqMatId() {
        return reqMatId;
    }

    public void setReqMatId(String[] reqMatId) {
        this.reqMatId = reqMatId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
    
}
