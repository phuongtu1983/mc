/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class VendorEvalDetailBean {

    private int detid;
    private int evalid;
    private int criid;
    private int evalResult;
    private String note;
    private String criName;
    private String address;
    private String phone;
    private String fax;
    private String presenter;
    private String fromDate;
    private String toDate;
    private int lastResult;
    private String orgName;
    private String resultString;
    private String vendorName;
    private String evalNumber;
    private String[] result;
    private String[] notes;

    public VendorEvalDetailBean() {
    }

    public int getCriid() {
        return criid;
    }

    public int getDetid() {
        return detid;
    }

    public int getEvalResult() {
        return evalResult;
    }

    public int getEvalid() {
        return evalid;
    }

    public String getNote() {
        return note;
    }

    public String getCriName() {
        return criName;
    }

    public void setCriid(int criid) {
        this.criid = criid;
    }

    public void setDetid(int detid) {
        this.detid = detid;
    }

    public void setEvalResult(int evalResult) {
        this.evalResult = evalResult;
    }

    public void setEvalid(int evalid) {
        this.evalid = evalid;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCriName(String criName) {
        this.criName = criName;
    }

    public String getAddress() {
        return address;
    }

    public String getFax() {
        return fax;
    }

    public String getFromDate() {
        return fromDate;
    }

    public int getLastResult() {
        return lastResult;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPresenter() {
        return presenter;
    }

    public String getResultString() {
        return resultString;
    }

    public String getToDate() {
        return toDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEvalNumber() {
        return evalNumber;
    }

    public void setEvalNumber(String evalNumber) {
        this.evalNumber = evalNumber;
    }

    public String[] getNotes() {
        return notes;
    }

    public String[] getResult() {
        return result;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public void setResult(String[] result) {
        this.result = result;
    }


}
