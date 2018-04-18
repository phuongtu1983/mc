/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReportBean;

import java.util.ArrayList;

/**
 *
 * @author PhuongTu
 */
public class BidEvalSumReportVendorBean {

    private String n1;
    private String vendor;
    private String amount3;
    private String amount4;
    private String amount5;
    private String deliveryDate;
    private String certificate;
    private String currency;
    private int besvId;
    private String reqNumber;
    public ArrayList arrMaterial;
    public int vendorKind;
    private double priceCertificate;

    public BidEvalSumReportVendorBean() {
    }

    public BidEvalSumReportVendorBean(BidEvalSumReportVendorBean bean) {
        this.n1 = bean.getN1();
        this.vendor = bean.getVendor();
        this.amount3 = bean.getAmount3();
        this.amount4 = bean.getAmount4();
        this.amount5 = bean.getAmount5();
        this.deliveryDate = bean.getDeliveryDate();
        this.certificate = bean.getCertificate();
        this.currency = bean.getCurrency();
        this.besvId = bean.getBesvId();
        this.reqNumber = bean.getReqNumber();
        this.vendorKind = bean.getVendorKind();
        if (bean.getArrMaterial() != null) {
            if (this.arrMaterial == null) {
                this.arrMaterial = new ArrayList();
            }
            for (int i = 0; i < bean.getArrMaterial().size(); i++) {
                this.arrMaterial.add(bean.getArrMaterial().get(i));
            }
        }
        this.priceCertificate = bean.getPriceCertificate();
    }

    public String getAmount3() {
        return amount3;
    }

    public void setAmount3(String amount3) {
        this.amount3 = amount3;
    }

    public String getAmount4() {
        return amount4;
    }

    public void setAmount4(String amount4) {
        this.amount4 = amount4;
    }

    public String getAmount5() {
        return amount5;
    }

    public void setAmount5(String amount5) {
        this.amount5 = amount5;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ArrayList getArrMaterial() {
        if (arrMaterial == null) {
            arrMaterial = new ArrayList();
        }
        return arrMaterial;
    }

    public void setArrMaterial(ArrayList arrMaterial) {
        this.arrMaterial = arrMaterial;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void addMaterial(BidEvalSumReportMaterialBean material) {
        if (this.arrMaterial == null) {
            this.arrMaterial = new ArrayList();
        }
        this.arrMaterial.add(material);
    }

    public int getBesvId() {
        return besvId;
    }

    public void setBesvId(int besvId) {
        this.besvId = besvId;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public int getVendorKind() {
        return vendorKind;
    }

    public void setVendorKind(int vendorKind) {
        this.vendorKind = vendorKind;
    }

    public double getPriceCertificate() {
        return priceCertificate;
    }

    public void setPriceCertificate(double priceCertificate) {
        this.priceCertificate = priceCertificate;
    }
}
