/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidClosingReportBean {

    //fields region
    private int bcrId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String reportNumber;
    private String createdDate;
    private String comments;
    private String closingDate;
    private String closingTime;
    private String endClosingDate;
    private String endClosingTime;

    //constructure region
    public BidClosingReportBean() {
    }

    public BidClosingReportBean(int bcrId) {
        this.bcrId = bcrId;
    }

    public BidClosingReportBean(int bcrId, String reportNumber, String createdDate, String comments) {
        this.bcrId = bcrId;
        this.reportNumber = reportNumber;
        this.createdDate = createdDate;
        this.comments = comments;
    }

    //properties region
    public int getBcrId() {
        return this.bcrId;
    }

    public void setBcrId(int bcrId) {
        this.bcrId = bcrId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getEndClosingDate() {
        return endClosingDate;
    }

    public String getEndClosingTime() {
        return endClosingTime;
    }

    public void setEndClosingDate(String endClosingDate) {
        this.endClosingDate = endClosingDate;
    }

    public void setEndClosingTime(String endClosingTime) {
        this.endClosingTime = endClosingTime;
    }

}
