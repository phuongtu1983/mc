/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EosDDetailBean {

    private String detId; // primary key
    private int eosdId;
    private int ematId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;

    //constructure region
    public EosDDetailBean() {
    }

    //properties region
    public String getDetId() {
        return this.detId;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public int getEosdId() {
        return this.eosdId;
    }

    public void setEosdId(int eosdId) {
        this.eosdId = eosdId;
    }

    public int getEmatId() {
        return this.ematId;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
    }

    public String getMatName() {
        return this.matName;
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

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }
}
