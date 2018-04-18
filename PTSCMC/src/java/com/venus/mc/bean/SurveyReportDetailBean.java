package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class SurveyReportDetailBean {

    private int detId;
    private int srId;
    private int equId;
    private double quantity;
    private String matName;
    private String usedCode;
    private String unitName;

    public SurveyReportDetailBean(int detId, int srId, int equId, double quantity, String matName, String usedCode, String unitName) {
        this.detId = detId;
        this.srId = srId;
        this.equId = equId;
        this.quantity = quantity;
        this.matName = matName;
        this.usedCode = usedCode;
        this.unitName = unitName;
    }

    public SurveyReportDetailBean(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getSrId() {
        return srId;
    }

    public void setSrId(int srId) {
        this.srId = srId;
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

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getMatName() {
        return matName;
    }

    public SurveyReportDetailBean() {
    }
}
