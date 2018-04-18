/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.intermemo;

import com.venus.mc.bean.RequestBean;

/**
 *
 * @author phuongtu
 */
public class IntermemoFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    private int reqId;
    private int createdEmp;
    private int createdOrg;
    private String requestNumber;
    private String createdDate;
    private int statusSuggest;
    private String subject;
    private String employeeName;
    private String organizationName;
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] presentQuantity;
    private String[] additionalQuantity;
    private String[] requestQuantity;
    private String[] note;
    private String[] materialKind;
    private int proId;
    private int approveEmp;
    private int assignedEmp;

    //constructure region
    public IntermemoFormBean() {
        super();
    // TODO Auto-generated constructor stub
    }

    public IntermemoFormBean(RequestBean bean) {
        this.reqId = bean.getReqId();
        this.createdEmp = bean.getCreatedEmp();
        this.requestNumber = bean.getRequestNumber();
        this.createdDate = bean.getCreatedDate();
        this.statusSuggest = bean.getStatusSuggest();
        this.subject = bean.getIntermemoSubject();
        this.employeeName = bean.getEmployeeName();
        this.organizationName = bean.getOrganizationName();
        this.createdOrg = bean.getCreatedOrg();
        this.proId = bean.getProId();
        this.assignedEmp = bean.getAssignedEmp();
    }

    //properties region
    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getRequestNumber() {
        return this.requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatusSuggest() {
        return this.statusSuggest;
    }

    public void setStatusSuggest(int statusSuggest) {
        this.statusSuggest = statusSuggest;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getPresentQuantity() {
        return presentQuantity;
    }

    public void setPresentQuantity(String[] presentQuantity) {
        this.presentQuantity = presentQuantity;
    }

    public String[] getAdditionalQuantity() {
        return additionalQuantity;
    }

    public void setAdditionalQuantity(String[] additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public String[] getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(String[] requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String[] getNote() {
        return note;
    }

    public void setNote(String[] note) {
        this.note = note;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public String[] getMaterialKind() {
        return materialKind;
    }

    public void setMaterialKind(String[] materialKind) {
        this.materialKind = materialKind;
    }

    public int getApproveEmp() {
        return approveEmp;
    }

    public void setApproveEmp(int approveEmp) {
        this.approveEmp = approveEmp;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getAssignedEmp() {
        return assignedEmp;
    }

    public void setAssignedEmp(int assignedEmp) {
        this.assignedEmp = assignedEmp;
    }
}
