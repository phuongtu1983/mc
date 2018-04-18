/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.mc.tenderletter.*;


/**
 *
 * @author mai vinh loc
 */
public class TechClarificationFormBean extends org.apache.struts.action.ActionForm {
 
    private int tcId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String tcNumber;
    private String subfix;
    private String createdDate;
    private String vendorName;
    private String discipline;
    private String notes;
    private int terId;

    //constructure region
    public TechClarificationFormBean() {
        super();
    }

    //properties region
    public int getTcId() {
        return this.tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public String getTcNumber() {
        return this.tcNumber;
    }

    public void setTcNumber(String tcNumber) {
        this.tcNumber = tcNumber;
    }

    public String getSubfix() {
        return this.subfix;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getNotes() {
        return notes;
    }

    public int getTerId() {
        return terId;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }
    
}
