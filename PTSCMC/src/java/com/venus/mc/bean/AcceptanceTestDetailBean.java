package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class AcceptanceTestDetailBean {

    private int detId;
    private int atId;
    private int equId;
    private double quantity;
    private String matName;
    private String usedCode;
    private String unitName;

    public AcceptanceTestDetailBean(int detId, int atId, int equId, double quantity, String matName, String usedCode, String unitName) {
        this.detId = detId;
        this.atId = atId;
        this.equId = equId;
        this.quantity = quantity;
        this.matName = matName;
        this.usedCode = usedCode;
        this.unitName = unitName;
    }

    public AcceptanceTestDetailBean(int detId) {
        this.detId = detId;
    }

    public String getMatName() {
        return matName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getAtId() {
        return atId;
    }

    public void setAtId(int atId) {
        this.atId = atId;
    }

    public int getEquId() {
        return equId;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public AcceptanceTestDetailBean() {
    }
}
