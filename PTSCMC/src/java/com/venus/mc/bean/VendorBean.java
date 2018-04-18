/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class VendorBean {

    private int venId;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String web;
    private String presenter;
    private int status;
    private String field;
    private String license;
    private String charterCapital;
    private String note;
    private String pospresenter;
    private String phonePresenter;
    private String subfix;
    private int detId;
    private int letId;
    private int tevId;
    private int kind;
    private int bidded;
    private int evalKind;
    private String emailPresenter;
    private String comments;
    private int orgHandle;

    public VendorBean() {
    }

    public void setPhonePresenter(String phonePresenter) {
        this.phonePresenter = phonePresenter;
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public String getPhonePresenter() {
        return phonePresenter;
    }

    public void setEvalKind(int evalKind) {
        this.evalKind = evalKind;
    }

    public int getEvalKind() {
        return evalKind;
    }

    public int getBidded() {
        return bidded;
    }

    public void setBidded(int bidded) {
        this.bidded = bidded;
    }

    public VendorBean(int venId) {
        this.venId = venId;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getWeb() {
        return web;
    }

    public String getCharterCapital() {
        return charterCapital;
    }

    public void setCharterCapital(String charterCapital) {
        this.charterCapital = charterCapital;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix;
    }

    public String getSubfix() {
        return subfix;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
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

    public String getPospresenter() {
        return pospresenter;
    }

    public void setPospresenter(String pospresenter) {
        this.pospresenter = pospresenter;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getEmailPresenter() {
        return emailPresenter;
    }

    public void setEmailPresenter(String emailPresenter) {
        this.emailPresenter = emailPresenter;
    }
    public static int KIND_NATIONAL = 1;
    public static int KIND_INTERNATIONAL = 2;
    public static String PBK = "Phong bi kin";
    public static String Fax = "Fax";
}
