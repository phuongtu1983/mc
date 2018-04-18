/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EdeliveryNoticeDetailBean {
//fields region
    private int detId; // primary key
    private int ednId; // foreign key : reference to delivery_notice(dn_id)
    private int ematId; // foreign key : reference to material(mat_id)
    private String matName;
    private String unit;
    private double quantity;
    private double price;

    //constructure region
    public EdeliveryNoticeDetailBean() {
    }

    public EdeliveryNoticeDetailBean(int detId) {
        this.detId = detId;
    }

    public EdeliveryNoticeDetailBean(int detId, double quantity) {
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

    public int getEdnId() {
        return this.ednId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public int getEmatId() {
        return this.ematId;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
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
}
