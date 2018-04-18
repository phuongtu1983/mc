/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class EmsvBean {

    private int emsvId;
    private String emsvNumber;
    private String createdDate;
    //private String content;
    private int createdEmpId;
    private String createdEmpName;
    private String contract;
    private String vendor;
    private int stoId;
    private String stoName;
    private int emrirId;
    private String emrirNumber;
    private String deliverer;
    private double total;

    public EmsvBean() {
    }

    public void setEmsvId(int emsvId) {
        this.emsvId = emsvId;
    }

    public int getEmsvId() {
        return emsvId;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContract() {
        return contract;
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

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setEmsvNumber(String emsvNumber) {
        this.emsvNumber = emsvNumber;
    }

    public String getEmsvNumber() {
        return emsvNumber;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public int getEmrirId() {
        return emrirId;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }

    public String getEmrirNumber() {
        return emrirNumber;
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

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }
}

