package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class SurveyReportMaterialBean {

    private int detId;
    private int srId;
    private int matId;
    private double qt;
    private String matName;
    private String code;

    public SurveyReportMaterialBean(int detId, int srId, int matId, int qt, String matName, String code) {
        this.detId = detId;
        this.srId = srId;
        this.matId = matId;
        this.qt = qt;
        this.matName = matName;
        this.code = code;
    }

    public SurveyReportMaterialBean(int detId) {
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

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setQt(double qt) {
        this.qt = qt;
    }

    public double getQt() {
        return qt;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public SurveyReportMaterialBean() {
    }
}
