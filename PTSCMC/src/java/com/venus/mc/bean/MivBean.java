
/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.bean;

public class MivBean {

    //fields region
    private int mivId; // primary key
    private String createdDate;
    private String mivNumber;
    private int creator; // foreign key : reference to employee(emp_id)
    private int receiver; // foreign key : reference to employee(emp_id)
    private int rfmId; // foreign key : reference to rfm(rfm_id)
    private int proId; // foreign key : reference to project(pro_id)
    private int orgId; // foreign key : reference to organization(org_id)
    private int stoId;// foreign key : reference to store(sto_id)
    private String description;
    private double total;
    private int type;
    private int mivKind;
    private String rfmNumber;
    private String whichUseName;
    private String storeName;
    private int requestOrg;
    private String creatorName;

    //constructure region
    public MivBean() {
    }

    public MivBean(int mivId) {
        this.mivId = mivId;
    }

    public MivBean(int mivId, String createdDate, String mivNumber, String description, double total, int type, int mivKind) {
        this.mivId = mivId;
        this.createdDate = createdDate;
        this.mivNumber = mivNumber;
        this.description = description;
        this.total = total;
        this.type = type;
        this.mivKind = mivKind;
    }

    //properties region
    public int getMivId() {
        return this.mivId;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getMivNumber() {
        return this.mivNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public int getCreator() {
        return this.creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getReceiver() {
        return this.receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getRfmId() {
        return this.rfmId;
    }

    public void setRfmId(int rfmId) {
        this.rfmId = rfmId;
    }

    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMivKind() {
        return this.mivKind;
    }

    public void setMivKind(int mivKind) {
        this.mivKind = mivKind;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public String getRfmNumber() {
        return rfmNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getWhichUseName() {
        return whichUseName;
    }

    public void setWhichUseName(String whichUseName) {
        this.whichUseName = whichUseName;
    }

    public int getRequestOrg() {
        return requestOrg;
    }

    public void setRequestOrg(int requestOrg) {
        this.requestOrg = requestOrg;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public static int KIND_COMPANY = 1;
    public static int KIND_PARTNER = 2;
    public static int TYPE_CCDC = 1;
    public static int TYPE_OTHER = 2;
    public static int TYPE_TSCDHH = 3;
}
