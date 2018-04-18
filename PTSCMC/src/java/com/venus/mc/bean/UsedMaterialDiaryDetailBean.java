/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.bean;

public class UsedMaterialDiaryDetailBean {

    //fields region
    private int detId; // primary key
    private int umsId; // foreign key : reference to used_material_store2(ums_id)
    private int matId; // foreign key : reference to material(mat_id)
    private double usedQuantity;
    private double currentQuantity;
    private int reqDetailId;
    private String matName;
    private String unit;
    private int stoId;
    private int orgId;
    private int empId;
    private String mivNumber;
    private String note;
    private int mivId;
    private int canNotDelete;

    //constructure region
    public UsedMaterialDiaryDetailBean() {
    }

    public UsedMaterialDiaryDetailBean(int detId) {
        this.detId = detId;
    }

    public UsedMaterialDiaryDetailBean(int detId, double usedQuantity) {
        this.detId = detId;
        this.usedQuantity = usedQuantity;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getUmsId() {
        return this.umsId;
    }

    public void setUmsId(int umsId) {
        this.umsId = umsId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getUsedQuantity() {
        return this.usedQuantity;
    }

    public void setUsedQuantity(double usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public int getMivId() {
        return mivId;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCanNotDelete() {
        return canNotDelete;
    }

    public void setCanNotDelete(int canNotDelete) {
        this.canNotDelete = canNotDelete;
    }
}
