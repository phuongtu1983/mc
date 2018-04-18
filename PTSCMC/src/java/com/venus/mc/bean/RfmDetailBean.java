package com.venus.mc.bean;

public class RfmDetailBean {

    //fields region
    private int detId; // primary key
    private int rfmId; // foreign key : reference to rfm(rfm_id)
    private int matId; // foreign key : reference to material(mat_id)
    private int msvId;
    private String msvNumber;
    private double quantity;
    private String unit;
    private String comment;
    private int reqDetailId;
    private String matName;
    private String matCode;
    private double price;
    private String requestNumber;
    private String storeName;
    private int stoId;
    private double reserveQuantity;
    private double availableQuantity;
    private double actualQuantity;
    private double qtTemp;
    private int kind;
    private int msrId;
    private String note;
    private int Stt;
    private String location;

    //constructure region
    public RfmDetailBean() {
    }

    public RfmDetailBean(int detId) {
        this.detId = detId;
    }

    public RfmDetailBean(int detId, String msvNumber, double quantity, String unit, String comment, int reqDetailId, String matName) {
        this.detId = detId;
        this.msvNumber = msvNumber;
        this.quantity = quantity;
        this.unit = unit;
        this.comment = comment;
        this.reqDetailId = reqDetailId;
        this.matName = matName;
    }

    public void setMsrId(int msrId) {
        this.msrId = msrId;
    }

    public int getMsrId() {
        return msrId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setStt(int Stt) {
        this.Stt = Stt;
    }

    public int getStt() {
        return Stt;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getRfmId() {
        return this.rfmId;
    }

    public void setRfmId(int rfmId) {
        this.rfmId = rfmId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMsvNumber() {
        return this.msvNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
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

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public double getActualQuantity() {
        return actualQuantity;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public double getReserveQuantity() {
        return reserveQuantity;
    }

    public void setActualQuantity(double actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public void setAvailableQuantity(double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setReserveQuantity(double reserveQuantity) {
        this.reserveQuantity = reserveQuantity;
    }

    public double getQtTemp() {
        return qtTemp;
    }

    public void setQtTemp(double qtTemp) {
        this.qtTemp = qtTemp;
    }

    public int getMsvId() {
        return msvId;
    }

    public void setMsvId(int msvId) {
        this.msvId = msvId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
