/// <summary>
/// Author : phuongtu
/// Created Date : 23/09/2009
/// </summary>
package com.venus.mc.bean;

public class MivDetailBean {

    //fields region
    private int detId; // primary key
    private int mivId; // foreign key : reference to miv(miv_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private int reqDetailId;
    private String matName;
    private String matCode;
    private int stoId;
    private int rfmDetId;
    private double preQuantity;
    private String note;

    //constructure region
    public MivDetailBean() {
    }

    public MivDetailBean(int detId) {
        this.detId = detId;
    }

    public MivDetailBean(int detId, String unit, double quantity, double price, double total, int reqDetailId) {
        this.detId = detId;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.reqDetailId = reqDetailId;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getMivId() {
        return this.mivId;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getRfmDetId() {
        return rfmDetId;
    }

    public void setRfmDetId(int rfmDetId) {
        this.rfmDetId = rfmDetId;
    }

    public double getPreQuantity() {
        return preQuantity;
    }

    public void setPreQuantity(double preQuantity) {
        this.preQuantity = preQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
