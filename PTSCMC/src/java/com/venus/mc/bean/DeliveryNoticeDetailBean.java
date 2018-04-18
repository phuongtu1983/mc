/// <summary>
/// Author : kngo
/// Created Date : 05/10/2009
/// </summary>
package com.venus.mc.bean;

public class DeliveryNoticeDetailBean {

    //fields region
    private int detId; // primary key
    private int dnId; // foreign key : reference to delivery_notice(dn_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
    private String unit;
    private double quantity;
    private double price;
    private int reqDetailId;
    private int status;

    //constructure region
    public DeliveryNoticeDetailBean() {
    }

    public DeliveryNoticeDetailBean(int detId) {
        this.detId = detId;
    }

    public DeliveryNoticeDetailBean(int detId, int quantity) {
        this.detId = detId;
        this.quantity = quantity;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDnId() {
        return this.dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return this.quantity;
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

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
