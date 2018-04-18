/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class ComEvalConditionBean {

    //fields region
    private int comId; // primary key
    private int cevId; // foreign key : reference to com_eval_vendor(cev_id)
    private String paymentMethod;
    private String guaranteeContract;
    private String customsCost;
    private String transportCost;
    private String otherTax;
    private String otherCost;
    private String totalEval;
    private String subTotal;
    private String freightCharge;
    private String testCertification;
    private String spareParts;
    private String grossPrice;
    private String discount;
    private String netPrice;
    private String shippingLocation;
    private String deliverySchedule;
    private String paymentTerms;
    private String warranty;
    private String potcDeviation;
    private int conclusion;

    //constructure region
    public ComEvalConditionBean() {
    }

    public ComEvalConditionBean(int comId) {
        this.comId = comId;
    }

    public ComEvalConditionBean(int comId, String paymentMethod, String guaranteeContract, String customsCost, String transportCost, String otherTax, String otherCost, String totalEval, String subTotal, String freightCharge, String testCertification, String spareParts, String grossPrice, String discount, String netPrice, String shippingLocation, String deliverySchedule, String paymentTerms, String warranty, String potcDeviation, int conclusion) {
        this.comId = comId;
        this.paymentMethod = paymentMethod;
        this.guaranteeContract = guaranteeContract;
        this.customsCost = customsCost;
        this.transportCost = transportCost;
        this.otherTax = otherTax;
        this.otherCost = otherCost;
        this.totalEval = totalEval;
        this.subTotal = subTotal;
        this.freightCharge = freightCharge;
        this.testCertification = testCertification;
        this.spareParts = spareParts;
        this.grossPrice = grossPrice;
        this.discount = discount;
        this.netPrice = netPrice;
        this.shippingLocation = shippingLocation;
        this.deliverySchedule = deliverySchedule;
        this.paymentTerms = paymentTerms;
        this.warranty = warranty;
        this.potcDeviation = potcDeviation;
        this.conclusion = conclusion;
    }

    //properties region
    public int getComId() {
        return this.comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getCevId() {
        return this.cevId;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getGuaranteeContract() {
        return this.guaranteeContract;
    }

    public void setGuaranteeContract(String guaranteeContract) {
        this.guaranteeContract = guaranteeContract;
    }

    public String getCustomsCost() {
        return this.customsCost;
    }

    public void setCustomsCost(String customsCost) {
        this.customsCost = customsCost;
    }

    public String getTransportCost() {
        return this.transportCost;
    }

    public void setTransportCost(String transportCost) {
        this.transportCost = transportCost;
    }

    public String getOtherTax() {
        return this.otherTax;
    }

    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    public String getOtherCost() {
        return this.otherCost;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public String getTotalEval() {
        return this.totalEval;
    }

    public void setTotalEval(String totalEval) {
        this.totalEval = totalEval;
    }

    public String getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getFreightCharge() {
        return this.freightCharge;
    }

    public void setFreightCharge(String freightCharge) {
        this.freightCharge = freightCharge;
    }

    public String getTestCertification() {
        return this.testCertification;
    }

    public void setTestCertification(String testCertification) {
        this.testCertification = testCertification;
    }

    public String getSpareParts() {
        return this.spareParts;
    }

    public void setSpareParts(String spareParts) {
        this.spareParts = spareParts;
    }

    public String getGrossPrice() {
        return this.grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice = grossPrice;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNetPrice() {
        return this.netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public String getShippingLocation() {
        return this.shippingLocation;
    }

    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
    }

    public String getDeliverySchedule() {
        return this.deliverySchedule;
    }

    public void setDeliverySchedule(String deliverySchedule) {
        this.deliverySchedule = deliverySchedule;
    }

    public String getPaymentTerms() {
        return this.paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getWarranty() {
        return this.warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getPotcDeviation() {
        return this.potcDeviation;
    }

    public void setPotcDeviation(String potcDeviation) {
        this.potcDeviation = potcDeviation;
    }

    public int getConclusion() {
        return this.conclusion;
    }

    public void setConclusion(int conclusion) {
        this.conclusion = conclusion;
    }
}
