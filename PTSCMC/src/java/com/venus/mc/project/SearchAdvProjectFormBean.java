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
public class SearchAdvProjectFormBean extends org.apache.struts.action.ActionForm {
   //fields region
    private int proId; // primary key
    private String name;
    private String startDate;
    private String endDate;
    private String comments;
    private int status;

    //constructure region
    public SearchAdvProjectFormBean() {
    }

    //properties region
    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
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
}
