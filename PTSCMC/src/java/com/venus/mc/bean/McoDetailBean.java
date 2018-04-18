/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class McoDetailBean {

    private int detId; // primary key
    private int mcoId; // foreign key : reference to material_carry_out(mco_id)
    private int equId; // foreign key : reference to equipment(equ_id)
    private String usedCode;
    private String nameVn;
    private String equName;
    private String unit;
    private double quantity;
    private String spec;

    //constructure region
    public McoDetailBean() {
    }

    public McoDetailBean(int detId) {
        this.detId = detId;
    }

    public McoDetailBean(int detId, String unit, int quantity, String spec) {
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

    public int getMcoId() {
        return this.mcoId;
    }

    public void setMcoId(int mcoId) {
        this.mcoId = mcoId;
    }

    public int getEquId() {
        return this.equId;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public String getUsedCode() {
        return this.usedCode;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public String getNameVn() {
        return this.nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public String getEquName() {
        return this.equName;
    }

    public void setEquName(String equName) {
        this.equName = equName;
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

    public String getSpec() {
        return this.spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
