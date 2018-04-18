/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.process.store.miv;

import com.venus.mc.bean.MivBean;

public class MivFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int mivId;
    private String createdDate;
    private String mivNumber;
    private int creator;
    private int receiver;
    private int rfmId;
    private int proId;
    private int orgId;
    private int stoId;
    private String description;
    private double total;
    private int type;
    private int mivKind;
    private String storeName;
    private String creatorName;
    private String whichUseName;
    private String rfmNumber;
    private String receiverName;
    private String[] mivDetId;
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] price;
    private String[] detTotal;
    private String[] rfmDetId;

    //constructure region
    public MivFormBean() {
        super();
    }

    public MivFormBean(MivBean bean) {
        this.mivId = bean.getMivId();
        this.mivNumber = bean.getMivNumber();
        this.createdDate = bean.getCreatedDate();
        this.creator = bean.getCreator();
        this.receiver = bean.getReceiver();
        this.rfmId = bean.getRfmId();
        this.proId = bean.getProId();
//        this.orgId = bean.getOrgId();
        this.orgId = bean.getRequestOrg();
        this.stoId = bean.getStoId();
        this.description = bean.getDescription();
        this.total = bean.getTotal();
        this.type = bean.getType();
        this.mivKind = bean.getMivKind();
        this.rfmNumber = bean.getRfmNumber();
        this.whichUseName = bean.getWhichUseName();
        this.storeName = bean.getStoreName();
        this.creatorName = bean.getCreatorName();
    }

    public MivFormBean(int mivId, String createdDate, String mivNumber, String description, double total, int type, int mivKind) {
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getMivDetId() {
        return mivDetId;
    }

    public void setMivDetId(String[] mivDetId) {
        this.mivDetId = mivDetId;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String[] getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(String[] detTotal) {
        this.detTotal = detTotal;
    }

    public String[] getRfmDetId() {
        return rfmDetId;
    }

    public void setRfmDetId(String[] rfmDetId) {
        this.rfmDetId = rfmDetId;
    }
}
