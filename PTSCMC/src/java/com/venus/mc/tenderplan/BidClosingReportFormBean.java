/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.mc.bean.BidClosingReportBean;

/**
 *
 * @author kngo
 */
public class BidClosingReportFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int bcrId;
    private int tenId;
    private String reportNumber;
    private String createdDate;
    private String comments;
    private String tenderNumber;
    private String closingDate;
    private String closingTime;
    private String endClosingDate;
    private String endClosingTime;
    private String[] bcgId;
    private String[] bidClosingEmployee;
    private String[] bidClosingPosition;
    private String[] bidClosingOrgName;
    private String[] bidClosingNote;
    private String[] vendorId;
    private String[] vendorBidded;

    //constructure region
    public BidClosingReportFormBean() {
        super();
    }

    public BidClosingReportFormBean(BidClosingReportBean bean) {
        this.bcrId = bean.getBcrId();
        this.reportNumber = bean.getReportNumber();
        this.createdDate = bean.getCreatedDate();
        this.comments = bean.getComments();
        this.tenId = bean.getTenId();
        this.closingDate = bean.getClosingDate();
        this.closingTime = bean.getClosingTime();
        this.endClosingDate = bean.getEndClosingDate();
        this.endClosingTime = bean.getEndClosingTime();
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

    public void setEndClosingTime(String endClosingTime) {
        this.endClosingTime = endClosingTime;
    }

    public void setEndClosingDate(String endClosingDate) {
        this.endClosingDate = endClosingDate;
    }

    public String getEndClosingTime() {
        return endClosingTime;
    }

    public String getEndClosingDate() {
        return endClosingDate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
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

    public String[] getBcgId() {
        return bcgId;
    }

    public void setBcgId(String[] bcgId) {
        this.bcgId = bcgId;
    }

    public String[] getBidClosingEmployee() {
        return bidClosingEmployee;
    }

    public void setBidClosingEmployee(String[] bidClosingEmployee) {
        this.bidClosingEmployee = bidClosingEmployee;
    }

    public String[] getBidClosingPosition() {
        return bidClosingPosition;
    }

    public void setBidClosingPosition(String[] bidClosingPosition) {
        this.bidClosingPosition = bidClosingPosition;
    }

    public String[] getVendorBidded() {
        return vendorBidded;
    }

    public void setVendorBidded(String[] vendorBidded) {
        this.vendorBidded = vendorBidded;
    }

    public String[] getVendorId() {
        return vendorId;
    }

    public void setVendorId(String[] vendorId) {
        this.vendorId = vendorId;
    }

    public String[] getBidClosingNote() {
        return bidClosingNote;
    }

    public String[] getBidClosingOrgName() {
        return bidClosingOrgName;
    }

    public void setBidClosingNote(String[] bidClosingNote) {
        this.bidClosingNote = bidClosingNote;
    }

    public void setBidClosingOrgName(String[] bidClosingOrgName) {
        this.bidClosingOrgName = bidClosingOrgName;
    }
}
