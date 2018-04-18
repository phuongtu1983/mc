/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.mc.bean.HandedReportBean;

/**
 *
 * @author thuhc
 */
public class HandedReportFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    private int hrId;
    private int rtId;
    private int createdEmpId;
    private String createdEmpName;
    private String createdDate;
    private int orgId;
    private String orgName;
    private String hrNumber;
    private String empReceiveName;
    private int empReceiveId;
    private String orgReceiveName;
    private int orgReceiveId;
    private String createdTime;
    private String createdLocation;
    private String[] detId;
    private int[] equId;
    private String[] useCode;
    private String[] equipmentName;
    private String[] quantity;
    private String[] specifications;
    private String[] comment;

    //constructure region
    public HandedReportFormBean() {
        super();
    }

    public HandedReportFormBean(HandedReportBean bean) {
        super();
        this.rtId = bean.getRtId();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.createdDate = bean.getCreatedDate();
        this.hrNumber = bean.getHrNumber();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
        this.createdTime = bean.getCreatedTime();
        this.createdLocation = bean.getCreatedLocation();
        this.hrId = bean.getHrId();
        this.orgReceiveId = bean.getOrgReceiveId();
        this.orgReceiveName = bean.getOrgReceiveName();
        this.empReceiveId = bean.getEmpReceiveId();
        this.empReceiveName = bean.getEmpReceiveName();

    }

    public void setEquId(int[] equId) {
        this.equId = equId;
    }

    public int[] getEquId() {
        return equId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setEquipmentName(String[] equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String[] getEquipmentName() {
        return equipmentName;
    }

    public String[] getUseCode() {
        return useCode;
    }

    public void setUseCode(String[] useCode) {
        this.useCode = useCode;
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

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String[] getComment() {
        return comment;
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

    public void setSpecifications(String[] specifications) {
        this.specifications = specifications;
    }

    public String[] getSpecifications() {
        return specifications;
    }

    public void setOrgReceiveId(int orgReceiveId) {
        this.orgReceiveId = orgReceiveId;
    }

    public int getOrgReceiveId() {
        return orgReceiveId;
    }

    public void setOrgReceiveName(String orgReceiveName) {
        this.orgReceiveName = orgReceiveName;
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
