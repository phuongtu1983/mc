/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalVendorBean {

    //fields region
    private int cevId; // primary key
    private int ceId; // foreign key : reference to com_eval(ce_id)
    private int terId; // foreign key : reference to vendor(ter_id)
    private int tenId;
    private String currency;
    private Double customTax;
    private Double costTransport;
    private Double otherTax;
    private Double otherCost;
    private Double priceCertificate;
    private Double priceToPort;
    private String sum;
    private int rand;
    private String vendorName;
    private int incoterm;
    private int venId;
    private Double rates;
    private Double ratesAfter;
    private int venKind;
    private String paymentMethod;
    private int form;
    private String incortemText;

    //constructure region
    public ComEvalVendorBean() {
    }

    public ComEvalVendorBean(int cevId) {
        this.cevId = cevId;
    }

    public void setPriceCertificate(Double priceCertificate) {
        this.priceCertificate = priceCertificate;
    }

    public void setPriceToPort(Double priceToPort) {
        this.priceToPort = priceToPort;
    }

    public Double getPriceToPort() {
        return priceToPort;
    }

    public Double getPriceCertificate() {
        return priceCertificate;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getForm() {
        return form;
    }

    //properties region
    public int getCevId() {
        return this.cevId;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
    }

    public int getCeId() {
        return this.ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public Double getCostTransport() {
        return costTransport;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public Double getOtherTax() {
        return otherTax;
    }

    public int getRand() {
        return rand;
    }

    public String getSum() {
        return sum;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTenId() {
        return tenId;
    }

    public int getTerId() {
        return terId;
    }

    public void setCostTransport(Double costTransport) {
        this.costTransport = costTransport;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public void setOtherTax(Double otherTax) {
        this.otherTax = otherTax;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public int getIncoterm() {
        return incoterm;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public void setCustomTax(Double customTax) {
        this.customTax = customTax;
    }

    public Double getCustomTax() {
        return customTax;
    }

    public void setRatesAfter(Double ratesAfter) {
        this.ratesAfter = ratesAfter;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public Double getRates() {
        return rates;
    }

    public Double getRatesAfter() {
        return ratesAfter;
    }

    public int getVenKind() {
        return venKind;
    }

    public void setVenKind(int venKind) {
        this.venKind = venKind;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIncortemText() {
        return incortemText;
    }

    public void setIncortemText(String incortemText) {
        this.incortemText = incortemText;
    }
}
