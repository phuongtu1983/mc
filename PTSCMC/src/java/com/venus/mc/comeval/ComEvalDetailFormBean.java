/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comeval;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalDetailFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    
    private int detId; // primary key
    private int terId; // foreign key : reference to tech_eval_vendor(ter_id)
    private int[] matId; // foreign key : reference to material(mat_id)
    private int tenId;
    private String propose;
    private String[] deliveryTime;
    private String provideDate;
    private String[] otherCondition;
    private String offer;
    private String[] spec;
    private double[] quantity;
    private String[] unit;
    private int[] evalTbe;
    private String nameVn;
    private String certificateAttach;
    private String result;
    private int cevId; // foreign key : reference to com_eval_vendor(cev_id)
    private Double[] price;
    private Double[] total;
    private String currency;
    private Double customTax;
    private Double costTransport;
    private Double otherTax;
    private Double otherCost;
    private Double priceCertificate;
    private Double priceToPort;
    private Double sum;
    private int rand;
    private int[] comDetId;
    private int venId;
    private Double rates;
    private Double ratesAfter;
    private int ceId;
    private Double[] priceAfter;
    private int createdEmp;
    private String paymentMethod;
    private int incoterm;

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    
    public int getCreatedEmp() {
        return createdEmp;
    }

    public int getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }
    
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getTerId() {
        return this.terId;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public int[] getMatId() {
        return this.matId;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public String getPropose() {
        return this.propose;
    }

    public void setPropose(String propose) {
        this.propose = propose;
    }

    public String[] getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(String[] deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String[] getOtherCondition() {
        return this.otherCondition;
    }

    public void setOtherCondition(String[] otherCondition) {
        this.otherCondition = otherCondition;
    }

    public String getOffer() {
        return this.offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String[] getSpec() {
        return this.spec;
    }

    public void setSpec(String[] spec) {
        this.spec = spec;
    }

    public double[] getQuantity() {
        return quantity;
    }

    public void setQuantity(double[] quantity) {
        this.quantity = quantity;
    }
   
    public String[] getUnit() {
        return this.unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public int[] getEvalTbe() {
        return this.evalTbe;
    }

    public void setEvalTbe(int[] evalTbe) {
        this.evalTbe = evalTbe;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public String getNameVn() {
        return nameVn;
    }

    public String getProvideDate() {
        return provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public String getCertificateAttach() {
        return certificateAttach;
    }

    public void setCertificateAttach(String certificateAttach) {
        this.certificateAttach = certificateAttach;
    }

     public ComEvalDetailFormBean() {
        super();

    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTenId() {
        return tenId;
    }

    public int getCevId() {
        return cevId;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
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

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public void setOtherTax(Double otherTax) {
        this.otherTax = otherTax;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public void setCostTransport(Double costTransport) {
        this.costTransport = costTransport;
    }

    public Double getSum() {
        return sum;
    }

    public int getRand() {
        return rand;
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

    public void setCustomTax(Double customTax) {
        this.customTax = customTax;
    }

    public Double getCustomTax() {
        return customTax;
    }

    public Double getRates() {
        return rates;
    }

    public Double getRatesAfter() {
        return ratesAfter;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public void setRatesAfter(Double ratesAfter) {
        this.ratesAfter = ratesAfter;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public int getCeId() {
        return ceId;
    }

    public void setPriceAfter(Double[] priceAfter) {
        this.priceAfter = priceAfter;
    }

    public Double[] getPriceAfter() {
        return priceAfter;
    }

}
