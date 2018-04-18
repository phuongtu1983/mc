/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

/**
 *
 * @author kngo
 */
public class SearchAdvEmrirFormBean extends org.apache.struts.action.ActionForm {

    private int emrirId;
    private String searchStartDate;
    private String searchEndDate;
    private int ednId; // foreign key : reference to contract(con_id)
    private String emrirNumber;

    public SearchAdvEmrirFormBean() {
        super();
    }

    //properties region
    public int getEmrirId() {
        return this.emrirId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
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

    public int getEdnId() {
        return this.ednId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public String getEmrirNumber() {
        return this.emrirNumber;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }
}
