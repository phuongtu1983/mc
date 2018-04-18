
/// <summary>
/// Author : phuongtu
/// Created Date : 29/09/2009
/// </summary>
package com.venus.mc.bean;

public class EmaterialStoreBean {

    //fields region
    private int emsId; // primary key
    private int stoId; // foreign key : reference to store(sto_id)
    private int ematId;
    private double price;
    private double reserveQuantity;
    private double availableQuantity;
    private double actualQuantity;

    public EmaterialStoreBean() {
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
    }

    public int getEmatId() {
        return ematId;
    }

    public void setEmsId(int emsId) {
        this.emsId = emsId;
    }

    public int getEmsId() {
        return emsId;
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
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
}
