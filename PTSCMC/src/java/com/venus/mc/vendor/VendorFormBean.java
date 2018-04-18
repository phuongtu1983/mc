/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor;

import com.venus.mc.bean.VendorBean;

/**
 *
 * @author phuongtu
 */
public class VendorFormBean extends org.apache.struts.action.ActionForm {

    private int venId;
    private String name;
    private String presenter;
    private String address;
    private String phone;
    private String fax;
    private int status;
    private String email;
    private String web;
    private String license;
    private String field;
    private String charterCapital;
    private String note;
    private String pospresenter;
    private String phonePresenter;
    private int kind;
    private String comments;
    private int orgHandle;

    /**
     *
     */
    public VendorFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public VendorFormBean(VendorBean bean) {
        this.venId = bean.getVenId();
        this.name = bean.getName();
        this.presenter = bean.getPresenter();
        this.address = bean.getAddress();
        this.phone = bean.getPhone();
        this.fax = bean.getFax();
        this.status = bean.getStatus();
        this.email = bean.getEmail();
        this.web = bean.getWeb();
        this.license = bean.getLicense();
        this.field = bean.getField();
        this.charterCapital = bean.getCharterCapital();
        this.comments = bean.getComments();
        this.pospresenter = bean.getPospresenter();
        this.phonePresenter = bean.getPhonePresenter();
        this.kind = bean.getKind();
        this.orgHandle = bean.getOrgHandle();
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

    public void setPhonePresenter(String phonePresenter) {
        this.phonePresenter = phonePresenter;
    }

    public String getPhonePresenter() {
        return phonePresenter;
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
}
