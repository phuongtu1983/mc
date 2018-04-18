/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.bean;

public class TenderEvaluateVendorBean {

    //fields region
    private int tevId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int venId; // foreign key : reference to vendor(ven_id)
    private int sent;
    private int bidded;
    private String biddedPage;
    private String biddedFoundation;
    private int biddedStatus;
    private String quoNo;
    private String quoDate;
    private String bidValidity;
    private int incoterm;
    private String vendorName;
    private String result;
    private int terId;
    private int stt;
    private String sum;
    private int rand;
    private int cevId;
    private String unit;
    private double quantity;
    private Double total;
    private Double rates;
    private String materialName;
    private int cemId;
    private int matId;
    private String deliveryTime;
    private int besvId;
    private String ratesAfter;
    private String paymentMethod;
    private String guaranteeContract;
    private int venKind;
    private String currency;
    private int ceId;
    private int cb;
    private String startDate;
    private String incortemText;

    //constructure region
    public TenderEvaluateVendorBean() {
    }

    public TenderEvaluateVendorBean(int tevId) {
        this.tevId = tevId;
    }

    public TenderEvaluateVendorBean(int tevId, int sent, int bidded, String biddedPage, String biddedFoundation, int biddedStatus, String quoNo, String quoDate, String bidValidity, int incoterm) {
        this.tevId = tevId;
        this.sent = sent;
        this.bidded = bidded;
        this.biddedPage = biddedPage;
        this.biddedFoundation = biddedFoundation;
        this.biddedStatus = biddedStatus;
        this.quoNo = quoNo;
        this.quoDate = quoDate;
        this.bidValidity = bidValidity;
        this.incoterm = incoterm;
    }

    //properties region
    public int getTevId() {
        return this.tevId;
    }

    public void setTevId(int tevId) {
        this.tevId = tevId;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public int getCb() {
        return cb;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getSent() {
        return this.sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public int getBidded() {
        return this.bidded;
    }

    public void setBidded(int bidded) {
        this.bidded = bidded;
    }

    public String getBiddedPage() {
        return this.biddedPage;
    }

    public void setBiddedPage(String biddedPage) {
        this.biddedPage = biddedPage;
    }

    public String getBiddedFoundation() {
        return this.biddedFoundation;
    }

    public void setBiddedFoundation(String biddedFoundation) {
        this.biddedFoundation = biddedFoundation;
    }

    public int getBiddedStatus() {
        return this.biddedStatus;
    }

    public void setBiddedStatus(int biddedStatus) {
        this.biddedStatus = biddedStatus;
    }

    public String getQuoNo() {
        return this.quoNo;
    }

    public void setQuoNo(String quoNo) {
        this.quoNo = quoNo;
    }

    public String getQuoDate() {
        return this.quoDate;
    }

    public void setQuoDate(String quoDate) {
        this.quoDate = quoDate;
    }

    public String getBidValidity() {
        return this.bidValidity;
    }

    public void setBidValidity(String bidValidity) {
        this.bidValidity = bidValidity;
    }

    public int getIncoterm() {
        return this.incoterm;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTerId() {
        return terId;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public int getRand() {
        return rand;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
    }

    public int getCevId() {
        return cevId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public Double getTotal() {
        return total;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setCemId(int cemId) {
        this.cemId = cemId;
    }

    public int getCemId() {
        return cemId;
    }

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getBesvId() {
        return besvId;
    }

    public void setBesvId(int besvId) {
        this.besvId = besvId;
    }

    public void setRatesAfter(String ratesAfter) {
        this.ratesAfter = ratesAfter;
    }

    public String getRatesAfter() {
        return ratesAfter;
    }

    public void setGuaranteeContract(String guaranteeContract) {
        this.guaranteeContract = guaranteeContract;
    }

    public String getGuaranteeContract() {
        return guaranteeContract;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getVenKind() {
        return venKind;
    }

    public void setVenKind(int venKind) {
        this.venKind = venKind;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCeId() {
        return ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIncortemText() {
        return incortemText;
    }

    public void setIncortemText(String incortemText) {
        this.incortemText = incortemText;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }
}
