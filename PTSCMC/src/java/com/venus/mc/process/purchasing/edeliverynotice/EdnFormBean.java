//mai vinh loc
package com.venus.mc.process.purchasing.edeliverynotice;

public class EdnFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int dnId; // primary key
    private String createdDate;
    private String dnNumber;
    private String contractNumber;
    private String expectedDate;
    private String deliveryPlace;
    private String deliveryPresenter;
    private int createdOrg;
    private String orgName;
    private int proId;
    private String proName;
    private int[] delDetId;
    private int[] detId; // primary key
    private int[] matId; // foreign key : reference to material(mat_id)
    private double[] quantity;
    private String[] unit;
    private String[] matName;
    private String[] matCode;
    private double[] price;
    private String[] note;

    //constructure region
    public EdnFormBean() {
    }

    public void setDelDetId(int[] delDetId) {
        this.delDetId = delDetId;
    }

    public int[] getDelDetId() {
        return delDetId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public String getDeliveryPresenter() {
        return deliveryPresenter;
    }

    public int[] getDetId() {
        return detId;
    }

    public int getDnId() {
        return dnId;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public String[] getMatCode() {
        return matCode;
    }

    public int[] getMatId() {
        return matId;
    }

    public String[] getMatName() {
        return matName;
    }

    public String[] getNote() {
        return note;
    }

    public double[] getPrice() {
        return price;
    }

    public double[] getQuantity() {
        return quantity;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setDeliveryPresenter(String deliveryPresenter) {
        this.deliveryPresenter = deliveryPresenter;
    }

    public void setDetId(int[] detId) {
        this.detId = detId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public void setMatCode(String[] matCode) {
        this.matCode = matCode;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public void setMatName(String[] matName) {
        this.matName = matName;
    }

    public void setNote(String[] note) {
        this.note = note;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public void setQuantity(double[] quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProId() {
        return proId;
    }

    public String getProName() {
        return proName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }
}
