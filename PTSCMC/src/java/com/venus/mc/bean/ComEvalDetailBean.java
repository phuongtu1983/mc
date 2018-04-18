/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class ComEvalDetailBean {

    //fields region
    private int detId; // primary key
    private int cevId; // foreign key : reference to com_eval_vendor(cev_id)
    private int matId; // foreign key : reference to material(mat_id)
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String unit;
    private double quantity;
    private String quantityText;
    private String suggestedSupplier;
    private String nameVn;
    private String stt;
    private String price;
    private String total;
    private String vendorName;
    private String currency;
    private String costTransport;
    private String otherTax;
    private String otherCost;
    private String sum;
    private int rand;
    private String priceAfter;
    private String matName;
    private String requestNumber;
    private String projectName;
    private String priceCur;

    //constructure region
    public ComEvalDetailBean() {
    }

    public ComEvalDetailBean(int detId) {
        this.detId = detId;
    }

    public ComEvalDetailBean(int detId, String unit, int quantity, String suggestedSupplier) {
        this.detId = detId;
        this.unit = unit;
        this.quantity = quantity;
        this.suggestedSupplier = suggestedSupplier;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getCevId() {
        return this.cevId;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
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

    public String getSuggestedSupplier() {
        return this.suggestedSupplier;
    }

    public void setSuggestedSupplier(String suggestedSupplier) {
        this.suggestedSupplier = suggestedSupplier;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getStt() {
        return stt;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPrice() {
        return price;
    }

    public String getTotal() {
        return total;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCostTransport() {
        return costTransport;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public String getOtherTax() {
        return otherTax;
    }

    public int getRand() {
        return rand;
    }

    public String getSum() {
        return sum;
    }

    public void setCostTransport(String costTransport) {
        this.costTransport = costTransport;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(String priceAfter) {
        this.priceAfter = priceAfter;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPriceCur() {
        return priceCur;
    }

    public void setPriceCur(String priceCur) {
        this.priceCur = priceCur;
    }

    public String getQuantityText() {
        return quantityText;
    }

    public void setQuantityText(String quantityText) {
        this.quantityText = quantityText;
    }
}
