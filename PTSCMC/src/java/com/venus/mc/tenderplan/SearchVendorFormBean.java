/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderplan;

/**
 *
 * @author phuongtu
 */
public class SearchVendorFormBean extends org.apache.struts.action.ActionForm {

    private String vendorName;
    private String fromDateContract;
    private String toDateContract;
    private int materialGroup;
    private String materialGroupName;

    //constructure region
    public SearchVendorFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getFromDateContract() {
        return fromDateContract;
    }

    public void setFromDateContract(String fromDateContract) {
        this.fromDateContract = fromDateContract;
    }

    public int getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(int materialGroup) {
        this.materialGroup = materialGroup;
    }

    public String getToDateContract() {
        return toDateContract;
    }

    public void setToDateContract(String toDateContract) {
        this.toDateContract = toDateContract;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getMaterialGroupName() {
        return materialGroupName;
    }

    public void setMaterialGroupName(String materialGroupName) {
        this.materialGroupName = materialGroupName;
    }
}
