/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.process.store.level2;

import com.venus.mc.bean.UsedMaterialDiaryBean;

public class UsedMaterialStore2FormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int umsId;
    private int sto2Id;
    private String updateDate;
    private String organizationName;
    private int createdEmp;
    private String usmNumber;
    private String location;
    private int proId;
    private int orgId;
    private String[] umsDetId;
    private String[] matId;
    private String[] currentQuantity;
    private String[] usedQuantity;
    private String[] reqDetailId;
    private String[] mivId;
    private String[] note;

    //constructure region
    public UsedMaterialStore2FormBean() {
    }

    public UsedMaterialStore2FormBean(UsedMaterialDiaryBean bean) {
        this.umsId = bean.getUmsId();
        this.updateDate = bean.getUpdateDate();
        this.sto2Id = bean.getSto2Id();
    }

    public UsedMaterialStore2FormBean(int umsId, String updateDate) {
        this.umsId = umsId;
        this.updateDate = updateDate;
    }

    //properties region
    public int getUmsId() {
        return this.umsId;
    }

    public void setUmsId(int umsId) {
        this.umsId = umsId;
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

    public String[] getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(String[] usedQuantity) {
        this.usedQuantity = usedQuantity;
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

    public String[] getMivId() {
        return mivId;
    }

    public void setMivId(String[] mivId) {
        this.mivId = mivId;
    }

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

    public String getUsmNumber() {
        return usmNumber;
    }

    public void setUsmNumber(String usmNumber) {
        this.usmNumber = usmNumber;
    }

    public String[] getNote() {
        return note;
    }

    public void setNote(String[] note) {
        this.note = note;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
