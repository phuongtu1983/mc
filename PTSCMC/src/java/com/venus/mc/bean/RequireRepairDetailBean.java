package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RequireRepairDetailBean {

    private int detId;
    private int rrId;
    private int equId;
    private double quantity;
    private String matName;
    private String usedCode;
    private String unitName;

    public RequireRepairDetailBean(int detId, int rrId, int equId, double quantity, String matName, String usedCode, String unitName) {
        this.detId = detId;
        this.rrId = rrId;
        this.equId = equId;
        this.quantity = quantity;
        this.matName = matName;
        this.usedCode = usedCode;
        this.unitName = unitName;
    }

    public RequireRepairDetailBean(int detId) {
        this.detId = detId;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getRrId() {
        return rrId;
    }

    public void setRrId(int rrId) {
        this.rrId = rrId;
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

    public RequireRepairDetailBean() {
    }
}
