/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

import com.venus.mc.bean.BidOpeningReportBean;

/**
 *
 * @author kngo
 */
public class BidOpeningReportFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int borId;
    private int tenId;
    private String reportNumber;
    private String createdDate;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String comments;
    private String tenderNumber;
    private String[] bogId;
    private String[] bidOpeningEmployee;
    private String[] bidOpeningPosition;
    private String[] bidOpeningOrgName;
    private String[] bidOpeningNote;
    private String[] vendorId;
    private String[] vendorBiddedPage;
    private String[] vendorBiddedFoundation;
    private String[] vendorBiddedStatus;
    private String[] vendorQuoNo;
    private String[] vendorQuoDate;
    private String[] vendorBidValidity;
    private String[] vendorIncoterm;

    //constructure region
    public BidOpeningReportFormBean() {
        super();
    }

    public BidOpeningReportFormBean(BidOpeningReportBean bean) {
        this.borId = bean.getBorId();
        this.reportNumber = bean.getReportNumber();
        this.createdDate = bean.getCreatedDate();
        this.comments = bean.getComments();
        this.tenId = bean.getTenId();
        this.startDate = bean.getStartDate();
        this.startTime = bean.getStartTime();
        this.endDate = bean.getEndDate();
        this.endTime = bean.getEndTime();
    }

    public int getBorId() {
        return borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }
    //properties region
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String[] getBidOpeningEmployee() {
        return bidOpeningEmployee;
    }

    public void setBidOpeningEmployee(String[] bidOpeningEmployee) {
        this.bidOpeningEmployee = bidOpeningEmployee;
    }

    public String[] getBidOpeningPosition() {
        return bidOpeningPosition;
    }

    public void setBidOpeningPosition(String[] bidOpeningPosition) {
        this.bidOpeningPosition = bidOpeningPosition;
    }

    public String[] getBogId() {
        return bogId;
    }

    public void setBogId(String[] bogId) {
        this.bogId = bogId;
    }

    public String[] getVendorId() {
        return vendorId;
    }

    public void setVendorId(String[] vendorId) {
        this.vendorId = vendorId;
    }

    public String[] getVendorBiddedStatus() {
        return vendorBiddedStatus;
    }

    public void setVendorBiddedStatus(String[] vendorBiddedStatus) {
        this.vendorBiddedStatus = vendorBiddedStatus;
    }

    public String[] getVendorBiddedPage() {
        return vendorBiddedPage;
    }

    public void setVendorBiddedPage(String[] vendorBiddedPage) {
        this.vendorBiddedPage = vendorBiddedPage;
    }

    public String[] getVendorBiddedFoundation() {
        return vendorBiddedFoundation;
    }

    public void setVendorBiddedFoundation(String[] vendorBiddedFoundation) {
        this.vendorBiddedFoundation = vendorBiddedFoundation;
    }

    public String[] getVendorQuoNo() {
        return vendorQuoNo;
    }

    public void setVendorQuoNo(String[] vendorQuoNo) {
        this.vendorQuoNo = vendorQuoNo;
    }

    public String[] getVendorQuoDate() {
        return vendorQuoDate;
    }

    public void setVendorQuoDate(String[] vendorQuoDate) {
        this.vendorQuoDate = vendorQuoDate;
    }

    public String[] getVendorIncoterm() {
        return vendorIncoterm;
    }

    public void setVendorIncoterm(String[] vendorIncoterm) {
        this.vendorIncoterm = vendorIncoterm;
    }

    public String[] getVendorBidValidity() {
        return vendorBidValidity;
    }

    public void setVendorBidValidity(String[] vendorBidValidity) {
        this.vendorBidValidity = vendorBidValidity;
    }

    public String[] getBidOpeningNote() {
        return bidOpeningNote;
    }

    public String[] getBidOpeningOrgName() {
        return bidOpeningOrgName;
    }

    public void setBidOpeningNote(String[] bidOpeningNote) {
        this.bidOpeningNote = bidOpeningNote;
    }

    public void setBidOpeningOrgName(String[] bidOpeningOrgName) {
        this.bidOpeningOrgName = bidOpeningOrgName;
    }

    public static int SEALED = 1;
    public static int NOTSEALED = 2;
}
