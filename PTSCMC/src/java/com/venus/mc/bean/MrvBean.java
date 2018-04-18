/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class MrvBean {

    private int mrvId;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private String mrvNumber;
    private int deliveryEmpId;
    private String deliveryEmpName;
    private int mrirId;
    private String mrirNumber;
    private int orgId;
    private String orgName;
    private int proId;
    private String proName;
    private String description;
    private int stoId;
    private String stoName;
    private double total;
    private String mivNumbers;

    public MrvBean() {

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

    public String getMivNumbers() {
        return mivNumbers;
    }

    public void setMivNumbers(String mivNumbers) {
        this.mivNumbers = mivNumbers;
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
}
