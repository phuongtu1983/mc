/// <summary>
/// Author : phuongtu
/// Created Date : 29/09/2009
/// </summary>
package com.venus.mc.bean;

public class MaterialStoreRequestBean {

    //fields region
    private int msrId; // primary key
    private int stoId; // foreign key : reference to store(sto_id)
    private int matId;
    private int msvId;
    private double price;
    private double reserveQuantity;
    private double availableQuantity;
    private double actualQuantity;
    private int reqDetailId;

    //constructure region
    public MaterialStoreRequestBean() {
    }

    public MaterialStoreRequestBean(int msrId) {
        this.msrId = msrId;
    }

    public MaterialStoreRequestBean(int msrId, int matId, double price, double reserveQuantity, double availableQuantity, double actualQuantity, int reqDetailId) {
        this.msrId = msrId;
        this.matId = matId;
        this.price = price;
        this.reserveQuantity = reserveQuantity;
        this.availableQuantity = availableQuantity;
        this.actualQuantity = actualQuantity;
        this.reqDetailId = reqDetailId;
    }

    //properties region
    public int getMsrId() {
        return this.msrId;
    }

    public void setMsrId(int msrId) {
        this.msrId = msrId;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getReserveQuantity() {
        return this.reserveQuantity;
    }

    public void setReserveQuantity(double reserveQuantity) {
        this.reserveQuantity = reserveQuantity;
    }

    public double getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getActualQuantity() {
        return this.actualQuantity;
    }

    public void setActualQuantity(double actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setMsvId(int msvId) {
        this.msvId = msvId;
    }

    public int getMsvId() {
        return msvId;
    }
}
