/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author PhuongTu
 */
public class TenderPlanCertificateBean {

    private String cerName;
    private int cerId;
    private int tenId;
    private int tcId;

    public TenderPlanCertificateBean() {

    }

    public TenderPlanCertificateBean(int tenId, int cerId) {
        this.tenId = tenId;
        this.cerId = cerId;
    }

    public int getCerId() {
        return cerId;
    }

    public void setCerId(int cerId) {
        this.cerId = cerId;
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }
}
