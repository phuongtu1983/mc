/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmcDetailBean {
    private int detId; // primary key
    private int emcId; // foreign key : reference to material_carry_out(mco_id)
    private String equipment;
    private String unit;
    private int quantity;
    private String spec;

    //constructure region
    public EmcDetailBean() {
    }

    public EmcDetailBean(int detId) {
        this.detId = detId;
    }

    public EmcDetailBean(int detId, String unit, int quantity, String spec) {
        this.detId = detId;
        this.unit = unit;
        this.quantity = quantity;
        this.spec = spec;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getEmcId() {
        return this.emcId;
    }

    public void setEmcId(int emcId) {
        this.emcId = emcId;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSpec() {
        return this.spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
