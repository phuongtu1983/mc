/*
 * PositionBean.java
 *
 * Created on March 7, 2007, 9:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author Administrator
 */
public class VendorContactBean {

    private int contId;
    private int venId;
    private String name;
    private String position;
    private String handPhone;
    private String homePhone;
    private String officePhone;
    private String birthday;
    private String email;

    public VendorContactBean() {

    }

    public void setContId(int contId) {
        this.contId = contId;
    }

    public int getContId() {
        return this.contId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getHandPhone() {
        return handPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public String getPosition() {
        return position;
    }

    public int getVenId() {
        return venId;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHandPhone(String handPhone) {
        this.handPhone = handPhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }
}
