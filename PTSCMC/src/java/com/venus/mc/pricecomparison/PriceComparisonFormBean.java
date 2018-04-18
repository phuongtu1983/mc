/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.pricecomparison;

/**
 *
 * @author mai vinh loc
 */
public class PriceComparisonFormBean extends org.apache.struts.action.ActionForm {

    private int pcId; // primary key
    private int tenId;
    private String tenNumber;
    private String createdDate;
    private String subject;
    private int createdEmp;
    private String[] regDetId;
    private int[] matId;
    private String[] latestPrice;
    private String[] internetPrice;
    private String[] proposedPrice;
    private String[] currency1;
    private String[] currency2;
    private String[] effectedDate;
    private String[] contractNumber;

    //constructure region
    public PriceComparisonFormBean() {
        super();
    }

    public void setCurrency2(String[] currency2) {
        this.currency2 = currency2;
    }

    public void setEffectedDate(String[] effectedDate) {
        this.effectedDate = effectedDate;
    }

    public void setContractNumber(String[] contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String[] getEffectedDate() {
        return effectedDate;
    }

    public String[] getContractNumber() {
        return contractNumber;
    }

    public void setCurrency1(String[] currency1) {
        this.currency1 = currency1;
    }

    public String[] getCurrency2() {
        return currency2;
    }

    public String[] getCurrency1() {
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

    public String[] getInternetPrice() {
        return internetPrice;
    }

    public String[] getLatestPrice() {
        return latestPrice;
    }

    public int[] getMatId() {
        return matId;
    }

    public String[] getProposedPrice() {
        return proposedPrice;
    }

    public String[] getRegDetId() {
        return regDetId;
    }

    public void setInternetPrice(String[] internetPrice) {
        this.internetPrice = internetPrice;
    }

    public void setLatestPrice(String[] latestPrice) {
        this.latestPrice = latestPrice;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public void setProposedPrice(String[] proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public void setRegDetId(String[] regDetId) {
        this.regDetId = regDetId;
    }
}
