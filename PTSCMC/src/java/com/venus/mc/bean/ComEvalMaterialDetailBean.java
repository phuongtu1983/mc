/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author Mai vinh loc
 */
public class ComEvalMaterialDetailBean {

    //fields region
    private int detId; // primary key
    private int cemId;
    private int matId; // foreign key : reference to material(mat_id)
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int terId;
    private int venId;
    private String nameVn;
    private String stt;
    private Double price;
    private Double priceCustom;
    private String priceCustomText;
    private Double priceTransport;
    private String priceTransportText;
    private Double priceTax;
    private String priceTaxText;
    private Double priceOtherCost;
    private Double priceCertificate;
    private Double priceToPort;
    private String priceOtherCostText;
    private String priceCertificateText;
    private String priceToPortText;
    private Double pricePtscmc;
    private String pricePtscmcText;
    private String total;
    private Double rates;
    private Double ratesAfter;
    private Double priceAfter;
    private String priceAfterText;
    private String vendorName;
    private String unit;
    private double quantity;
    private String quantityText;
    private int result;
    private int evalTbe;
    private int detIdTp;
    private String status;
    private String requestNumber;
    private String priceText;
    private String totalText;
    private String priceCurText;
    //phuongtu
    private String projectName;
    private String currency;

    //constructure region
    public ComEvalMaterialDetailBean() {
    }

    public void setPriceToPort(Double priceToPort) {
        this.priceToPort = priceToPort;
    }

    public void setPriceCertificate(Double priceCertificate) {
        this.priceCertificate = priceCertificate;
    }

    public Double getPriceToPort() {
        return priceToPort;
    }

    public Double getPriceCertificate() {
        return priceCertificate;
    }

    public void setDetIdTp(int detIdTp) {
        this.detIdTp = detIdTp;
    }

    public int getDetIdTp() {
        return detIdTp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ComEvalMaterialDetailBean(int detId) {
        this.detId = detId;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getCemId() {
        return cemId;
    }

    public void setCemId(int cemId) {
        this.cemId = cemId;
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

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getPrice() {
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

    public Double getPriceCustom() {
        return priceCustom;
    }

    public Double getPriceOtherCost() {
        return priceOtherCost;
    }

    public Double getPricePtscmc() {
        return pricePtscmc;
    }

    public Double getPriceTax() {
        return priceTax;
    }

    public Double getPriceTransport() {
        return priceTransport;
    }

    public int getTerId() {
        return terId;
    }

    public void setPriceCustom(Double priceCustom) {
        this.priceCustom = priceCustom;
    }

    public void setPriceOtherCost(Double priceOtherCost) {
        this.priceOtherCost = priceOtherCost;
    }

    public void setPricePtscmc(Double pricePtscmc) {
        this.pricePtscmc = pricePtscmc;
    }

    public void setPriceTax(Double priceTax) {
        this.priceTax = priceTax;
    }

    public void setPriceTransport(Double priceTransport) {
        this.priceTransport = priceTransport;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public Double getRatesAfter() {
        return ratesAfter;
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

    public Double getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(Double priceAfter) {
        this.priceAfter = priceAfter;
    }

    public String getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public int getEvalTbe() {
        return evalTbe;
    }

    public void setEvalTbe(int evalTbe) {
        this.evalTbe = evalTbe;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPriceCurText() {
        return priceCurText;
    }

    public void setPriceCurText(String priceCurText) {
        this.priceCurText = priceCurText;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceCustomText() {
        return priceCustomText;
    }

    public void setPriceCustomText(String priceCustomText) {
        this.priceCustomText = priceCustomText;
    }

    public String getPriceOtherCostText() {
        return priceOtherCostText;
    }

    public void setPriceOtherCostText(String priceOtherCostText) {
        this.priceOtherCostText = priceOtherCostText;
    }

    public String getPricePtscmcText() {
        return pricePtscmcText;
    }

    public void setPricePtscmcText(String pricePtscmcText) {
        this.pricePtscmcText = pricePtscmcText;
    }

    public String getPriceTaxText() {
        return priceTaxText;
    }

    public void setPriceTaxText(String priceTaxText) {
        this.priceTaxText = priceTaxText;
    }

    public String getPriceTransportText() {
        return priceTransportText;
    }

    public void setPriceTransportText(String priceTransportText) {
        this.priceTransportText = priceTransportText;
    }

    public String getQuantityText() {
        return quantityText;
    }

    public void setQuantityText(String quantityText) {
        this.quantityText = quantityText;
    }

    public String getPriceAfterText() {
        return priceAfterText;
    }

    public void setPriceAfterText(String priceAfterText) {
        this.priceAfterText = priceAfterText;
    }

    public String getPriceCertificateText() {
        return priceCertificateText;
    }

    public void setPriceCertificateText(String priceCertificateText) {
        this.priceCertificateText = priceCertificateText;
    }

    public String getPriceToPortText() {
        return priceToPortText;
    }

    public void setPriceToPortText(String priceToPortText) {
        this.priceToPortText = priceToPortText;
    }
    //1:Dat ; 2: Khong Dat; Khong chao gia
    public static int RESULT1 = 1;
    public static int RESULT2 = 2;
    public static int RESULT3 = 3;
}
