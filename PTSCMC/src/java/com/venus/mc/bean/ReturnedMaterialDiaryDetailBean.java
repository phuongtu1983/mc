/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.bean;

public class ReturnedMaterialDiaryDetailBean {

    //fields region
    private int detId; // primary key
    private int rmsId; // foreign key : reference to returned_material_store2(rms_id)
    private int matId; // foreign key : reference to material(mat_id)
    private double returnedQuantity;
    private double currentQuantity;
    private int reqDetailId;
    private int umsDetId;
    private String status;
    private String note;
    private String matName;
    private String umsNumber;
    private String unit;
//    private int stoId;
//    private int orgId;
//    private int empId;

    //constructure region
    public ReturnedMaterialDiaryDetailBean() {
    }

    public ReturnedMaterialDiaryDetailBean(int detId) {
        this.detId = detId;
    }

    public ReturnedMaterialDiaryDetailBean(int detId, double returnedQuantity) {
        this.detId = detId;
        this.returnedQuantity = returnedQuantity;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getRmsId() {
        return this.rmsId;
    }

    public void setRmsId(int rmsId) {
        this.rmsId = rmsId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getReturnedQuantity() {
        return this.returnedQuantity;
    }

    public void setReturnedQuantity(double returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
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

//    public int getStoId() {
//        return stoId;
//    }
//
//    public void setStoId(int stoId) {
//        this.stoId = stoId;
//    }
//
//    public int getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(int empId) {
//        this.empId = empId;
//    }
//
//    public int getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(int orgId) {
//        this.orgId = orgId;
//    }
//
//    public String getMivNumber() {
//        return mivNumber;
//    }
//
//    public void setMivNumber(String mivNumber) {
//        this.mivNumber = mivNumber;
//    }
//
//    public int getMivId() {
//        return mivId;
//    }
//
//    public void setMivId(int mivId) {
//        this.mivId = mivId;
//    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUmsDetId() {
        return umsDetId;
    }

    public void setUmsDetId(int umsDetId) {
        this.umsDetId = umsDetId;
    }

    public String getUmsNumber() {
        return umsNumber;
    }

    public void setUmsNumber(String umsNumber) {
        this.umsNumber = umsNumber;
    }
}
