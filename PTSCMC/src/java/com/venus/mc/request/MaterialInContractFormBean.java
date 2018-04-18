/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.request;

/**
 *
 * @author phuongtu
 */
public class MaterialInContractFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int matId;
    private String matCode;
    private String matName;
    private String unit;
    private double quantity;
    private int conId;
    private String conNumber;
    private String provideDate;
    private int reqId;
    private String reqNumber;
    private double conQuantity;
    private String materialGroup;
    private double price;
    private int venId;
    private String venName;
    private int reqDetailId;
    private String responseEmployeeName;
    private String assignedEmployeeName;
    private String project;
    private String quantityString;
    private String priceText;
    private int conDetId;

    //constructure region
    public MaterialInContractFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public void setConDetId(int conDetId) {
        this.conDetId = conDetId;
    }

    public int getConDetId() {
        return conDetId;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
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

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getProvideDate() {
        return provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getConQuantity() {
        return conQuantity;
    }

    public void setConQuantity(double conQuantity) {
        this.conQuantity = conQuantity;
    }

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getResponseEmployeeName() {
        return responseEmployeeName;
    }

    public void setResponseEmployeeName(String responseEmployeeName) {
        this.responseEmployeeName = responseEmployeeName;
    }

    public String getAssignedEmployeeName() {
        return assignedEmployeeName;
    }

    public void setAssignedEmployeeName(String assignedEmployeeName) {
        this.assignedEmployeeName = assignedEmployeeName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getQuantityString() {
        return quantityString;
    }

    public void setQuantityString(String quantityString) {
        this.quantityString = quantityString;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }
}
