/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

import java.sql.Date;

/**
 *
 * @author kngo
 */
public class TechEvalConditionBean {

    //fields region
    private int tecId; // primary key
    private int detId; // foreign key : reference to tech_eval_detail(det_id)
    private String certificateAttach;
    private Date deliveryTime;
    private String testCertificate;
    private String origin;
    private String moreRequire;
    private String warranty;
    private int conlusion;

    //constructure region
    public TechEvalConditionBean() {
    }

    public TechEvalConditionBean(int tecId) {
        this.tecId = tecId;
    }

    public TechEvalConditionBean(int tecId, String certificateAttach, Date deliveryTime, String testCertificate, String origin, String moreRequire, String warranty, int conlusion) {
        this.tecId = tecId;
        this.certificateAttach = certificateAttach;
        this.deliveryTime = deliveryTime;
        this.testCertificate = testCertificate;
        this.origin = origin;
        this.moreRequire = moreRequire;
        this.warranty = warranty;
        this.conlusion = conlusion;
    }

    //properties region
    public int getTecId() {
        return this.tecId;
    }

    public void setTecId(int tecId) {
        this.tecId = tecId;
    }

    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public String getCertificateAttach() {
        return this.certificateAttach;
    }

    public void setCertificateAttach(String certificateAttach) {
        this.certificateAttach = certificateAttach;
    }

    public Date getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getTestCertificate() {
        return this.testCertificate;
    }

    public void setTestCertificate(String testCertificate) {
        this.testCertificate = testCertificate;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMoreRequire() {
        return this.moreRequire;
    }

    public void setMoreRequire(String moreRequire) {
        this.moreRequire = moreRequire;
    }

    public String getWarranty() {
        return this.warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public int getConlusion() {
        return this.conlusion;
    }

    public void setConlusion(int conlusion) {
        this.conlusion = conlusion;
    }
}
