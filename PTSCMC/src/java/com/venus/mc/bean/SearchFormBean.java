/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class SearchFormBean extends org.apache.struts.action.ActionForm {

    private int searchid;
    private String searchvalue;
    private String searchvalues;
    private int extraSearchId;
    private String extraSearchValue;

    public SearchFormBean() {
    }

    public int getSearchid() {
        return searchid;
    }

    public void setSearchid(int searchid) {
        this.searchid = searchid;
    }

    public String getSearchvalue() {
        return searchvalue;
    }

    public void setSearchvalues(String searchvalues) {
        this.searchvalues = searchvalues;
    }

    public String getSearchvalues() {
        return searchvalues;
    }

    public void setSearchvalue(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    public int getExtraSearchId() {
        return extraSearchId;
    }

    public void setExtraSearchId(int extraSearchId) {
        this.extraSearchId = extraSearchId;
    }

    public String getExtraSearchValue() {
        return extraSearchValue;
    }

    public void setExtraSearchValue(String extraSearchValue) {
        this.extraSearchValue = extraSearchValue;
    }
}
