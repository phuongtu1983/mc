/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalMaterialDetailFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int detId; // primary key
    private int[] terId; // foreign key : reference to tech_eval_vendor(ter_id)
    private int[] matId; // foreign key : reference to material(mat_id)
    private int tenId;
    private int cemId;
    private String materialName;
    private double[] quantity;
    private String[] unit;
    private Double[] price;
    private Double[] priceCustom;
    private Double[] priceTransport;
    private Double[] priceTax;
    private Double[] priceOtherCost;
    private Double[] priceCertificate;
    private Double[] priceToPort;
    private Double[] priceAfter;
    private Double[] pricePtscmc;
    private Double[] total;
    private String[] vendorName;
    private String paymentMethod;
    private String guaranteeContract;
    private int[] result;
    private int[] comDetId;
    private int[] detIdTp;
    private int venId;
    private Double rates;
    private Double ratesAfter;
    private String currency;
    private int incoterm;

    public void setDetIdTp(int[] detIdTp) {
        this.detIdTp = detIdTp;
    }

    public int[] getDetIdTp() {
        return detIdTp;
    }

    public void setPriceToPort(Double[] priceToPort) {
        this.priceToPort = priceToPort;
    }

    public void setPriceCertificate(Double[] priceCertificate) {
        this.priceCertificate = priceCertificate;
    }

    public Double[] getPriceToPort() {
        return priceToPort;
    }

    public Double[] getPriceCertificate() {
        return priceCertificate;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public int getIncoterm() {
        return incoterm;
    }

    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int[] getTerId() {
        return this.terId;
    }

    public void setTerId(int[] terId) {
        this.terId = terId;
    }

    public int[] getMatId() {
        return this.matId;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTenId() {
        return tenId;
    }

    public Double[] getPrice() {
        return price;
    }

    public void setPrice(Double[] price) {
        this.price = price;
    }

    public Double[] getTotal() {
        return total;
    }

    public void setTotal(Double[] total) {
        this.total = total;
    }

    public int getCemId() {
        return cemId;
    }

    public String getGuaranteeContract() {
        return guaranteeContract;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double[] getPriceCustom() {
        return priceCustom;
    }

    public Double[] getPriceOtherCost() {
        return priceOtherCost;
    }

    public Double[] getPricePtscmc() {
        return pricePtscmc;
    }

    public Double[] getPriceTax() {
        return priceTax;
    }

    public Double[] getPriceTransport() {
        return priceTransport;
    }

    public double[] getQuantity() {
        return quantity;
    }

    public int[] getResult() {
        return result;
    }

    public String[] getVendorName() {
        return vendorName;
    }

    public void setCemId(int cemId) {
        this.cemId = cemId;
    }

    public void setGuaranteeContract(String guaranteeContract) {
        this.guaranteeContract = guaranteeContract;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPriceCustom(Double[] priceCustom) {
        this.priceCustom = priceCustom;
    }

    public void setPriceOtherCost(Double[] priceOtherCost) {
        this.priceOtherCost = priceOtherCost;
    }

    public void setPricePtscmc(Double[] pricePtscmc) {
        this.pricePtscmc = pricePtscmc;
    }

    public void setPriceTax(Double[] priceTax) {
        this.priceTax = priceTax;
    }

    public void setPriceTransport(Double[] priceTransport) {
        this.priceTransport = priceTransport;
    }

    public void setQuantity(double[] quantity) {
        this.quantity = quantity;
    }

    public void setResult(int[] result) {
        this.result = result;
    }

    public void setVendorName(String[] vendorName) {
        this.vendorName = vendorName;
    }

    public int[] getComDetId() {
        return comDetId;
    }

    public void setComDetId(int[] comDetId) {
        this.comDetId = comDetId;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public void setRatesAfter(Double ratesAfter) {
        this.ratesAfter = ratesAfter;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public Double getRatesAfter() {
        return ratesAfter;
    }

    public Double getRates() {
        return rates;
    }

    public void setPriceAfter(Double[] priceAfter) {
        this.priceAfter = priceAfter;
    }

    public Double[] getPriceAfter() {
        return priceAfter;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getUnit() {
        return unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
