/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.DateUtil;

/**
 *
 * @author kngo
 */
public class SearchAdvMrirFormBean extends org.apache.struts.action.ActionForm {

    private int mrirId;
    private String searchStartDate;
    private String searchEndDate;
    private String reqNumber;
    private String dnNumber;
    private String mrirNumber;
    private String blNo;
    private String invoiceNo;
    private String plNo;

    public SearchAdvMrirFormBean() {
        super();       
    }

    //properties region
    public int getMrirId() {
        return this.mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public String getSearchStartDate() {
        return this.searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return this.searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public String getMrirNumber() {
        return this.mrirNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getBlNo() {
        return this.blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPlNo() {
        return this.plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }
}
