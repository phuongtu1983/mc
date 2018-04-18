/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.bean;

public class TenderPlanDetailBean {

    //fields region
    private int detId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String unit;
    private double quantity;
    private String provideDate;
    private int reqDetailId;
    private String matName;
    private String matOrigin;
    private double price;
    private double total;
    private int stt;

    //constructure region
    public TenderPlanDetailBean() {
    }

    public TenderPlanDetailBean(int detId) {
        this.detId = detId;
    }

    public TenderPlanDetailBean(int detId, String unit, double quantity, String provideDate, int reqDetailId) {
        this.detId = detId;
        this.unit = unit;
        this.quantity = quantity;
        this.provideDate = provideDate;
        this.reqDetailId = reqDetailId;
    }

    public TenderPlanDetailBean(int detId, int tenId, int matId, String unit, double quantity, String provideDate, int reqDetailId, String matName, String matOrigin, double price, double total, int stt) {
        this.detId = detId;
        this.tenId = tenId;
        this.matId = matId;
        this.unit = unit;
        this.quantity = quantity;
        this.provideDate = provideDate;
        this.reqDetailId = reqDetailId;
        this.matName = matName;
        this.matOrigin = matOrigin;
        this.price = price;
        this.total = total;
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getTenId() {
        return this.tenId;
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

    public String getProvideDate() {
        return this.provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatOrigin() {
        return matOrigin;
    }

    public void setMatOrigin(String matOrigin) {
        this.matOrigin = matOrigin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
