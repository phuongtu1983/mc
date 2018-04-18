/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class EmsvMaterialBean {

    private int detId;
    private int emsvId;
    private int ematId;
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private int stoId;

    public EmsvMaterialBean() {
    }

    public EmsvMaterialBean(int detId, int emsvId, int ematId, String matName, String matCode, String unit, double quantity, double price, double total, int stoId) {
        this.detId = detId;
        this.emsvId = emsvId;
        this.ematId = ematId;
        this.matName = matName;
        this.matCode = matCode;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.stoId = stoId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
    }

    public int getEmatId() {
        return ematId;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setEmsvId(int emsvId) {
        this.emsvId = emsvId;
    }

    public int getEmsvId() {
        return emsvId;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
