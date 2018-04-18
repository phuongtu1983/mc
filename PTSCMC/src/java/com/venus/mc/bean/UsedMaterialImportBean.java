package com.venus.mc.bean;

public class UsedMaterialImportBean {

    //fields region
    private int detId; // primary key
    private int stoId; // foreign key : reference to store(sto_id)
    private int matId;
    private int mivId;
    private int dmvId;
    private double importQuantity;
    private int reqDetailId;

    //constructure region
    public UsedMaterialImportBean() {
    }

    public UsedMaterialImportBean(int detId, int stoId, int matId, int mivId, int dmvId, double importQuantity, int reqDetailId) {
        this.detId = detId;
        this.stoId = stoId;
        this.matId = matId;
        this.mivId = mivId;
        this.dmvId = dmvId;
        this.importQuantity = importQuantity;
        this.reqDetailId = reqDetailId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public int getMivId() {
        return mivId;
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setDmvId(int dmvId) {
        this.dmvId = dmvId;
    }

    public int getDmvId() {
        return dmvId;
    }

    public void setImportQuantity(double importQuantity) {
        this.importQuantity = importQuantity;
    }

    public double getImportQuantity() {
        return importQuantity;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }
}
