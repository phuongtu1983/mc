/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class RequestDetailBean {
    //fields region

    private int detId; // primary key
    private int reqId; // foreign key : reference to request(req_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private int conId; // foreign key : reference to contract(con_id)
    private String unit;
    private double presentQuantity;
    private double additionalQuantity;
    private double requestQuantity;
    private String provideDate;
    private int checkAppro;
    private int stockAppro;
    private int plandepAppro;
    private int bomAppro;
    private String intermemoNote;
    private String step;
    private int stepId;
    private int materialKind;
    private double remainQuantity;
    private int tenId;
    private int empId;
    private int orgId;

    //constructure region
    public RequestDetailBean() {
    }

    public RequestDetailBean(int detId) {
        this.detId = detId;
    }

    public RequestDetailBean(int detId, String unit, double presentQuantity, double additionalQuantity, double requestQuantity, String provideDate, int checkAppro, int stockAppro, int plandepAppro, int bomAppro, String intermemoNote) {
        this.detId = detId;
        this.unit = unit;
        this.presentQuantity = presentQuantity;
        this.additionalQuantity = additionalQuantity;
        this.requestQuantity = requestQuantity;
        this.provideDate = provideDate;
        this.checkAppro = checkAppro;
        this.plandepAppro = plandepAppro;
        this.intermemoNote = intermemoNote;
        this.stockAppro = stockAppro;
        this.bomAppro = bomAppro;
        this.remainQuantity = this.requestQuantity;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return this.matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPresentQuantity() {
        return this.presentQuantity;
    }

    public void setPresentQuantity(double presentQuantity) {
        this.presentQuantity = presentQuantity;
    }

    public double getAdditionalQuantity() {
        return this.additionalQuantity;
    }

    public void setAdditionalQuantity(double additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public double getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(double requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String getProvideDate() {
        return this.provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public int getCheckAppro() {
        return this.checkAppro;
    }

    public void setCheckAppro(int checkAppro) {
        this.checkAppro = checkAppro;
    }

    public int getPlandepAppro() {
        return this.plandepAppro;
    }

    public void setPlandepAppro(int plandepAppro) {
        this.plandepAppro = plandepAppro;
    }

    public String getIntermemoNote() {
        return this.intermemoNote;
    }

    public void setIntermemoNote(String intermemoNote) {
        this.intermemoNote = intermemoNote;
    }

    public String getStep() {
        return this.step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getStepId() {
        return this.stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getStockAppro() {
        return stockAppro;
    }

    public void setStockAppro(int stockAppro) {
        this.stockAppro = stockAppro;
    }

    public int getBomAppro() {
        return bomAppro;
    }

    public void setBomAppro(int bomAppro) {
        this.bomAppro = bomAppro;
    }

    public int getMaterialKind() {
        return materialKind;
    }

    public void setMaterialKind(int materialKind) {
        this.materialKind = materialKind;
    }

    public double getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(double remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
