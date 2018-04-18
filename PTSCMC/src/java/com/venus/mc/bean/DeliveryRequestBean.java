
/// <summary>
/// Author : phuongtu
/// Created Date : 24/08/2009
/// </summary>
package com.venus.mc.bean;

public class DeliveryRequestBean {

    //fields region
    private int drId; // primary key
    private int venId; // foreign key : reference to vendor(ven_id)
    private String deliveryNumber;
    private String createdDate;
    private double total;
    private String currency;
    private String deliveryDate;
    private String effectedDate;
    private String note;
    private double totalNotVAT;
    private double sumVAT;
    private String volume;
    private int createdEmp;
    private String signDate;
    private int responseEmp;
    private int followEmp;
    private int processStatus;
    private String processStatusText;
    private String delivery;

    //constructure region
    public DeliveryRequestBean() {
    }

    public DeliveryRequestBean(int drId) {
        this.drId = drId;
    }

    public DeliveryRequestBean(int drId, String deliveryNumber, String createdDate, long total, String currency, String deliveryDate, String note, String signDate) {
        this.drId = drId;
        this.deliveryNumber = deliveryNumber;
        this.total = total;
        this.currency = currency;
        this.deliveryDate = deliveryDate;
        this.createdDate = createdDate;
        this.note = note;
        this.signDate = signDate;
        this.effectedDate = effectedDate;
    }

    //properties region
    public int getDrId() {
        return this.drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getDeliveryNumber() {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(String subfix) {
        this.deliveryNumber = subfix;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

    public double getTotalNotVAT() {
        return totalNotVAT;
    }

    public void setTotalNotVAT(double totalNotVAT) {
        this.totalNotVAT = totalNotVAT;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public int getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(int followEmp) {
        this.followEmp = followEmp;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessStatusText() {
        return processStatusText;
    }

    public void setProcessStatusText(String processStatusText) {
        this.processStatusText = processStatusText;
    }

    public int getResponseEmp() {
        return responseEmp;
    }

    public void setResponseEmp(int responseEmp) {
        this.responseEmp = responseEmp;
    }

    public String getEffectedDate() {
        return effectedDate;
    }

    public void setEffectedDate(String effectedDate) {
        this.effectedDate = effectedDate;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
