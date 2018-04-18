/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalMaterialBean {

    //fields region
    private int cemId; // primary key
    private int ceId; // foreign key : reference to com_eval(ce_id)
    private int tenId; // foreign key : reference to vendor(ter_id)
    private int venId;
    private int matId;
    private String vendorName;
    private int incoterm;
    private double total;
    private Double rates;
    private String ratesAfter;
    private String paymentMethod;
    private String guaranteeContract;
    private String currency;
    private int terId;
    private int createdEmp;
    private int form;

    //constructure region
    public ComEvalMaterialBean() {
    }

    public ComEvalMaterialBean(int form, int cemId, int ceId, int tenId, int venId, int matId, String vendorName, int incoterm, double total, Double rates, String ratesAfter, String paymentMethod, String guaranteeContract, String currency, int terId, int createdEmp) {
        this.cemId = cemId;
        this.ceId = ceId;
        this.tenId = tenId;
        this.venId = venId;
        this.matId = matId;
        this.vendorName = vendorName;
        this.incoterm = incoterm;
        this.total = total;
        this.rates = rates;
        this.ratesAfter = ratesAfter;
        this.paymentMethod = paymentMethod;
        this.guaranteeContract = guaranteeContract;
        this.currency = currency;
        this.terId = terId;
        this.createdEmp = createdEmp;
        this.form = form;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getForm() {
        return form;
    }

    public int getCemId() {
        return cemId;
    }

    public void setCemId(int cemId) {
        this.cemId = cemId;
    }

    public int getCeId() {
        return this.ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public int getIncoterm() {
        return incoterm;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getVenId() {
        return venId;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public void setRatesAfter(String ratesAfter) {
        this.ratesAfter = ratesAfter;
    }

    public Double getRates() {
        return rates;
    }

    public String getRatesAfter() {
        return ratesAfter;
    }

    public String getGuaranteeContract() {
        return guaranteeContract;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setGuaranteeContract(String guaranteeContract) {
        this.guaranteeContract = guaranteeContract;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getVendorName() {
        return vendorName;
    }

    public int getTerId() {
        return terId;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
