/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emsv;

import com.venus.mc.bean.EmsvBean;

/**
 *
 * @author thcao
 */
public class EmsvFormBean extends org.apache.struts.action.ActionForm {

    private int emsvId;
    private String emsvNumber;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private int stoId;
    private String stoName;
    private int emrirId;
    private String emrirNumber;
    private String contract;
    private String deliverer;
    private double total;
    private int kind;
    private int[] ematId;
    private String[] unit;
    private String[] quantity;
    private String[] price;

    public EmsvFormBean() {
        super();
    }

    public EmsvFormBean(EmsvBean bean) {
        this.emsvId = bean.getEmsvId();
        this.emsvNumber = bean.getEmsvNumber();
        this.createdDate = bean.getCreatedDate();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.stoId = bean.getStoId();
        this.stoName = bean.getStoName();
        this.emrirId = bean.getEmrirId();
        this.emrirNumber = bean.getEmrirNumber();
        this.contract = bean.getContract();
        this.total = bean.getTotal();
        this.deliverer = bean.getDeliverer();
    }

    public void setEmsvId(int emsvId) {
        this.emsvId = emsvId;
    }

    public int getEmsvId() {
        return emsvId;
    }

    public void setEmsvNumber(String emsvNumber) {
        this.emsvNumber = emsvNumber;
    }

    public String getEmsvNumber() {
        return emsvNumber;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpId(int createEmpId) {
        this.createdEmpId = createEmpId;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getStoId() {
        return stoId;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContract() {
        return contract;
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

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getStoName() {
        return stoName;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDeliverer() {
        return deliverer;
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

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}
