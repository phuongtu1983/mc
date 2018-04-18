/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.project;

import java.sql.Date;

/**
 *
 * @author kngo
 */
public class ProjectFormBean extends org.apache.struts.action.ActionForm {

    private int proId; // primary key
    private String name;
    private String name_en;
    private String startDate;
    private String endDate;
    private String qc_name;
    private String investorsName;
    private String comments;
    private int status;
    private String abbreviate;

    //constructure region
    public ProjectFormBean() {
    }

    //properties region
    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public void setInvestorsName(String investorsName) {
        this.investorsName = investorsName;
    }

    public String getInvestorsName() {
        return investorsName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQc_name() {
        return qc_name;
    }

    public void setQc_name(String qc_name) {
        this.qc_name = qc_name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }
}
