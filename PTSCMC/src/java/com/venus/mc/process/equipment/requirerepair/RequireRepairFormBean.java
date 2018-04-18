/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requirerepair;

import com.venus.mc.bean.RequireRepairBean;

/**
 *
 * @author mai vinh loc
 */
public class RequireRepairFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int rrId;
    private int createdEmp;
    private String createdDate;
    private String requireDate;
    private String rrNumber;
    private String comment;
    private String requireOrg;
    private String[] reqDetId;
    private String[] matId;
    private String[] equId;
    private String[] unit;
    private String[] usedCode;
    private String[] quantity;

    //constructure region
    public RequireRepairFormBean() {
        super();

    }

    public RequireRepairFormBean(RequireRepairBean bean) {
        this.rrId = bean.getRrId();
        this.createdEmp = bean.getCreatedEmp();
        this.createdDate = bean.getCreatedDate();
        this.requireDate = bean.getRequireDate();
        this.rrNumber = bean.getRrNumber();
        this.comment = bean.getComment();
        this.requireOrg = bean.getRequireOrg();
        
    }

    public void setEquId(String[] equId) {
        this.equId = equId;
    }

    public String[] getEquId() {
        return equId;
    }

    public int getRrId() {
        return rrId;
    }

    public void setRrId(int rrId) {
        this.rrId = rrId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRrNumber() {
        return rrNumber;
    }

    public void setRrNumber(String rrNumber) {
        this.rrNumber = rrNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public String getRequireOrg() {
        return requireOrg;
    }

    public void setRequireOrg(String requireOrg) {
        this.requireOrg = requireOrg;
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
}
