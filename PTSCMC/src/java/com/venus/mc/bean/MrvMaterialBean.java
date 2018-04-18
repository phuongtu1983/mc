/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class MrvMaterialBean {

    private int detId;
    private int mrvId;
    private int matId;
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private int stoId;

    public MrvMaterialBean() {
    }

    public MrvMaterialBean(int detId, int mrvId, int matId, String matName, String unit, double quantity, double price, double total) {
        this.detId = detId;
        this.mrvId = mrvId;
        this.matId = matId;
        this.matName = matName;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
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

    public void setMrvId(int mrvId) {
        this.mrvId = mrvId;
    }

    public int getMrvId() {
        return mrvId;
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
}
