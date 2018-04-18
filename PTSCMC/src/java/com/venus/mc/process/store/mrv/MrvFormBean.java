/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrv;

import com.venus.mc.bean.MsvBean;

/**
 *
 * @author thcao
 */
public class MrvFormBean extends org.apache.struts.action.ActionForm {

    private int mrvId;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private String mrvNumber;
    private int deliveryEmpId;
    private String deliveryEmpName;
    private int mrirId;
    private String mrirNumber;
    private int proId;
    private String proName;
    private int orgId;
    private String orgName;
    private String description;
    private int stoId;
    private String stoName;
    private double total;
    private String mivNumbers;
    private int[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] price;

    public MrvFormBean() {
        super();
    }

    public MrvFormBean(MsvBean bean) {
        this.mrvId = bean.getMsvId();
        this.createdDate = bean.getCreatedDate();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.mrvNumber = bean.getMsvNumber();
        this.deliveryEmpId = bean.getDeliveryEmpId();
        this.deliveryEmpName = bean.getDeliveryEmpName();
        this.mrirId = bean.getMrirId();
        this.mrirNumber = bean.getMrirNumber();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
        this.description = bean.getDescription();
        this.stoId = bean.getStoId();
        this.stoName = bean.getStoName();
        this.total = bean.getTotal();
        this.mivNumbers = bean.getMivNumbers();
        this.proId = bean.getProId();
        this.proName = bean.getProName();

    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setDeliveryEmpId(int deliveryEmpId) {
        this.deliveryEmpId = deliveryEmpId;
    }

    public int getDeliveryEmpId() {
        return deliveryEmpId;
    }

    public void setDeliveryEmpName(String deliveryEmpName) {
        this.deliveryEmpName = deliveryEmpName;
    }

    public String getDeliveryEmpName() {
        return deliveryEmpName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getMivNumbers() {
        return mivNumbers;
    }

    public void setMivNumbers(String mivNumbers) {
        this.mivNumbers = mivNumbers;
    }

    public void setMrvId(int mrvId) {
        this.mrvId = mrvId;
    }

    public int getMrvId() {
        return mrvId;
    }

    public void setMrvNumber(String mrvNumber) {
        this.mrvNumber = mrvNumber;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getMrvNumber() {
        return mrvNumber;
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

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public int getMrirId() {
        return mrirId;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getMrirNumber() {
        return mrirNumber;
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

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public int[] getMatId() {
        return matId;
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
}
