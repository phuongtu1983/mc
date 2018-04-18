/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class DmvMaterialBean {

    private int detId;
    private int dmvId;
    private int matId;
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private int stoId;
    private int reqDetailId;

    public DmvMaterialBean() {
    }

    public DmvMaterialBean(int detId, int dmvId, int matId, String matName, String matCode, String unit, int quantity, double price, double total, int stoId, int reqDetailId) {
        this.detId = detId;
        this.dmvId = dmvId;
        this.matId = matId;
        this.matName = matName;
        this.matCode = matCode;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.stoId = stoId;
        this.reqDetailId = reqDetailId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public int getMatId() {
        return matId;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setDmvId(int dmvId) {
        this.dmvId = dmvId;
    }

    public int getDmvId() {
        return dmvId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getStoId() {
        return stoId;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }
}
