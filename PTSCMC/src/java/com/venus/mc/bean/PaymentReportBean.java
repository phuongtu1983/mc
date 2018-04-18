/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author PhuongTu
 */
public class PaymentReportBean {

    private String project;
    private String request;
    private String mrir;
    private String msv;
    private String msvDate;
    private String mrirDate;

    public String getMrir() {
        return mrir;
    }

    public void setMrir(String mrir) {
        this.mrir = mrir;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMsvDate() {
        return msvDate;
    }

    public void setMsvDate(String msvDate) {
        this.msvDate = msvDate;
    }

    public String getMrirDate() {
        return mrirDate;
    }

    public void setMrirDate(String mrirDate) {
        this.mrirDate = mrirDate;
    }
}
