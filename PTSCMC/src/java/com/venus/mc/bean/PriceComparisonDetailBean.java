/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class PriceComparisonDetailBean {

    //fields region
    private String detId; // primary key
    private int pcId; // foreign key
    private int matId;
    private String matName;
    private String unit;
    private String latestPrice;
    private String internetPrice;
    private String proposedPrice;
    private int stt;
    private String contractNumber;
    private String effectedDate;
    private String currency1;
    private String currency2;

    //constructure region
    public PriceComparisonDetailBean() {
    }

    public PriceComparisonDetailBean(String detId, int pcId, int matId, String matName, String unit, String latestPrice, String internetPrice, String proposedPrice, int stt) {
        this.detId = detId;
        this.pcId = pcId;
        this.matId = matId;
        this.matName = matName;
        this.unit = unit;
        this.latestPrice = latestPrice;
        this.internetPrice = internetPrice;
        this.proposedPrice = proposedPrice;
        this.stt = stt;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getEffectedDate() {
        return effectedDate;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setEffectedDate(String effectedDate) {
        this.effectedDate = effectedDate;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getDetId() {
        return detId;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getInternetPrice() {
        return internetPrice;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public int getMatId() {
        return matId;
    }

    public int getPcId() {
        return pcId;
    }

    public void setInternetPrice(String internetPrice) {
        this.internetPrice = internetPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public void setProposedPrice(String proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public String getProposedPrice() {
        return proposedPrice;
    }
}
