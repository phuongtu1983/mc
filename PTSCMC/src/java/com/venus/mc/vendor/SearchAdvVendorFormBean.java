/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor;

/**
 *
 * @author phuongtu
 */
public class SearchAdvVendorFormBean extends org.apache.struts.action.ActionForm {

    private String venId;
    private String name;
    private String presenter;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String web;
    private String charterCapital;

    public SearchAdvVendorFormBean() {
    }

    public String getVenId() {
        return venId;
    }

    public void setVenId(String venId) {
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

    public String getCharterCapital() {
        return charterCapital;
    }

    public void setCharterCapital(String charterCapital) {
        this.charterCapital = charterCapital;
    }
}
