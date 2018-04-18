/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TenderLetterDetailBean {

    //fields region
    private int detId; // primary key
    private int tenId; // foreign key : reference to tender_tenter(ten_id)
    private int venId; // foreign key : reference to vendor(ven_id)
    private int letId;
    private int tevId;
    private String subfix;
    private int form;
    private String date;
    private String date1;
    private int kindVendor;
    private String vendorName;

    //constructure region
    public TenderLetterDetailBean() {
    }

    public TenderLetterDetailBean(int detId) {
        this.detId = detId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public TenderLetterDetailBean(int detId, String subfix) {
        this.detId = detId;
        this.subfix = subfix;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getSubfix() {
        return this.subfix;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix;
    }

    public void setLetId(int letId) {
        this.letId = letId;
    }

    public int getLetId() {
        return letId;
    }

    public int getTevId() {
        return tevId;
    }

    public void setTevId(int tevId) {
        this.tevId = tevId;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getKindVendor() {
        return kindVendor;
    }

    public void setKindVendor(int kindVendor) {
        this.kindVendor = kindVendor;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
}
