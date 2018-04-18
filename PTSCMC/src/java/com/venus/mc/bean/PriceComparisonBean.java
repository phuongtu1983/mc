/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class PriceComparisonBean {
    //fields region

    private int pcId; // primary key
    private int tenId;
    private String tenNumber;
    private String createdDate;
    private String subject;
    private int createdEmp;
    private String currency1;
    private String currency2;

    //constructure region
    public PriceComparisonBean() {
    }

    public PriceComparisonBean(int pcId, int tenId, String tenNumber, String createdDate, String subject, int createdEmp, String currency1, String currency2) {
        this.pcId = pcId;
        this.tenId = tenId;
        this.tenNumber = tenNumber;
        this.createdDate = createdDate;
        this.subject = subject;
        this.createdEmp = createdEmp;
        this.currency1 = currency1;
        this.currency2 = currency2;
    }

    
    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getTenNumber() {
        return tenNumber;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public int getPcId() {
        return pcId;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public String getSubject() {
        return subject;
    }
}
