/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.process.store.level2;

import com.venus.mc.bean.ReturnedMaterialDiaryBean;

public class ReturnedMaterialStore2FormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int rmsId;
    private int sto2Id;
    private String updateDate;
    private String organizationName;
    private int createdEmp;
    private String rsmNumber;
    private int proId;
    private int orgId;
    private String[] rmsDetId;
    private String[] matId;
    private String[] currentQuantity;
    private String[] returnedQuantity;
    private String[] reqDetailId;
    private String[] umsDetId;
    private String[] status;
    private String[] note;

    //constructure region
    public ReturnedMaterialStore2FormBean() {
    }

    public ReturnedMaterialStore2FormBean(ReturnedMaterialDiaryBean bean) {
        this.rmsId = bean.getRmsId();
        this.updateDate = bean.getUpdateDate();
        this.sto2Id = bean.getSto2Id();
    }

    public ReturnedMaterialStore2FormBean(int rmsId, String updateDate) {
        this.rmsId = rmsId;
        this.updateDate = updateDate;
    }

    //properties region
    public int getRmsId() {
        return this.rmsId;
    }

    public void setRmsId(int rmsId) {
        this.rmsId = rmsId;
    }

    public int getSto2Id() {
        return this.sto2Id;
    }

    public void setSto2Id(int sto2Id) {
        this.sto2Id = sto2Id;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String[] getUmsDetId() {
        return umsDetId;
    }

    public void setUmsDetId(String[] umsDetId) {
        this.umsDetId = umsDetId;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(String[] returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public String[] getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(String[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String[] getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(String[] currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

//    public String[] getMivId() {
//        return mivId;
//    }
//
//    public void setMivId(String[] mivId) {
//        this.mivId = mivId;
//    }
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

    public String getRsmNumber() {
        return rsmNumber;
    }

    public void setRsmNumber(String rsmNumber) {
        this.rsmNumber = rsmNumber;
    }

    public String[] getNote() {
        return note;
    }

    public void setNote(String[] note) {
        this.note = note;
    }

    public String[] getRmsDetId() {
        return rmsDetId;
    }

    public void setRmsDetId(String[] rmsDetId) {
        this.rmsDetId = rmsDetId;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }
}
