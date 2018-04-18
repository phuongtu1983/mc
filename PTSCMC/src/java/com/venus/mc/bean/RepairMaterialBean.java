/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class RepairMaterialBean {
//fields region

    private int detId;
    private int rmId; // primary key
    private int rpId; // foreign key : reference to delivery_notice(dn_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;

    //constructure region
    public RepairMaterialBean() {
    }

    public RepairMaterialBean(int rmId, int rpId, int matId, String matName, String matCode, String unit, double quantity) {
        this.rmId = rmId;
        this.rpId = rpId;
        this.matId = matId;
        this.matName = matName;
        this.matCode = matCode;
        this.unit = unit;
        this.quantity = quantity;
    }

    public RepairMaterialBean(int detId) {
        this.detId = detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getMatId() {
        return matId;
    }

    public String getMatName() {
        return matName;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getRmId() {
        return rmId;
    }

    public int getRpId() {
        return rpId;
    }

    public String getUnit() {
        return unit;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setRmId(int rmId) {
        this.rmId = rmId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
