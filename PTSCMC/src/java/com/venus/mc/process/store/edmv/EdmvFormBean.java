/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv;


import com.venus.mc.bean.EdmvBean;

/**
 *
 * @author thcao
 */
public class EdmvFormBean extends org.apache.struts.action.ActionForm {

    private int edmvId;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private String edmvNumber;
    private String deliverer;
    private String contract;
    private String vendor;
    private int ednId;
    private int receiveEmpId;
    private String receiveEmpName;
    private int orgId;
    private String orgName;
    private String dmvOrder;
    private String description;
    private int stoId;
    private String stoName;
    private int proId;
    private String proName;
    private double total;
    private int kind;
    private int emsvId;
    private String emsvNumber;
    private int emivId;
    private String emivNumber;
    private int emrirId;
    private String emrirNumber;
    private int erfmId;
    private String erfmNumber;
    private int[] ematId;
    private String[] unit;
    private String[] quantity;
    private String[] price;

    public EdmvFormBean() {
        super();
    }

    public EdmvFormBean(EdmvBean bean) {
        this.edmvId = bean.getEdmvId();
        this.createdDate = bean.getCreatedDate();
        this.edmvNumber = bean.getEdmvNumber();
        this.deliverer = bean.getDeliverer();

        this.contract = bean.getContract();
        this.ednId = bean.getEdnId();
        this.receiveEmpId = bean.getReceiveEmpId();
        this.receiveEmpName = bean.getReceiveEmpName();
        this.vendor = bean.getVendor();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
        this.dmvOrder = bean.getDmvOrder();
        this.description = bean.getDescription();
        this.stoId = bean.getStoId();
        this.stoName = bean.getStoName();
        this.total = bean.getTotal();
        this.kind = bean.getKind();
        this.emsvId = bean.getEmsvId();
        this.emsvNumber = bean.getEmsvNumber();
        this.emivId = bean.getEmivId();
        this.emivNumber = bean.getEmivNumber();
        this.emrirId = bean.getEmrirId();
        this.emrirNumber = bean.getEmrirNumber();
        this.erfmId = bean.getErfmId();
        this.erfmNumber = bean.getErfmNumber();

        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.proId = bean.getProId();
        this.proName = bean.getProName();
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContract() {
        return contract;
    }

    public void setEdmvId(int edmvId) {
        this.edmvId = edmvId;
    }

    public int getEdmvId() {
        return edmvId;
    }

    public void setEdmvNumber(String edmvNumber) {
        this.edmvNumber = edmvNumber;
    }

    public String getEdmvNumber() {
        return edmvNumber;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public int getEdnId() {
        return ednId;
    }

    public void setEmivId(int emivId) {
        this.emivId = emivId;
    }

    public int getEmivId() {
        return emivId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public int getEmrirId() {
        return emrirId;
    }

    public void setEmsvId(int emsvId) {
        this.emsvId = emsvId;
    }

    public int getEmsvId() {
        return emsvId;
    }

    public void setErfmId(int erfmId) {
        this.erfmId = erfmId;
    }

    public int getErfmId() {
        return erfmId;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDmvOrder(String dmvOrder) {
        this.dmvOrder = dmvOrder;
    }

    public String getDmvOrder() {
        return dmvOrder;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getStoName() {
        return stoName;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setReceiveEmpId(int receiveEmpId) {
        this.receiveEmpId = receiveEmpId;
    }

    public int getReceiveEmpId() {
        return receiveEmpId;
    }

    public void setReceiveEmpName(String receiveEmpName) {
        this.receiveEmpName = receiveEmpName;
    }

    public String getReceiveEmpName() {
        return receiveEmpName;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setEmatId(int[] ematId) {
        this.ematId = ematId;
    }

    public int[] getEmatId() {
        return ematId;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getPrice() {
        return price;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setEmivNumber(String emivNumber) {
        this.emivNumber = emivNumber;
    }

    public String getEmivNumber() {
        return emivNumber;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }

    public String getEmrirNumber() {
        return emrirNumber;
    }

    public void setEmsvNumber(String emsvNumber) {
        this.emsvNumber = emsvNumber;
    }

    public String getEmsvNumber() {
        return emsvNumber;
    }

    public void setErfmNumber(String erfmNumber) {
        this.erfmNumber = erfmNumber;
    }

    public String getErfmNumber() {
        return erfmNumber;
    }
}
