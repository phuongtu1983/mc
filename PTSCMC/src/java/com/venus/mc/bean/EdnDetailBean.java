//mai vinh loc
package com.venus.mc.bean;

public class EdnDetailBean {

    //fields region
    private int detId; // primary key
    private int dnId;
    private int matId; // foreign key : reference to material(mat_id)
    private double quantity;
    private String unit;
    private String matName;
    private String matCode;
    private double price;
    private String note;

    //constructure region
    public EdnDetailBean() {
    }

    public EdnDetailBean(int detId) {
        this.detId = detId;
    }

    public EdnDetailBean(int detId, int dnId, double quantity, String unit, String matCode, String note, String matName) {
        this.detId = detId;
        this.dnId = dnId;
        this.quantity = quantity;
        this.unit = unit;
        this.matCode = matCode;
        this.note = note;
        this.matName = matName;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getDnId() {
        return dnId;
    }

    public String getNote() {
        return note;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
}
