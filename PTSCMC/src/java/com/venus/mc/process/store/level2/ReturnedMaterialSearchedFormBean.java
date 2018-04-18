/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.process.store.level2;

public class ReturnedMaterialSearchedFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int ms2rId;
    private int sto2Id;
    private int matId;
    private double currentQuantity;
    private int reqDetailId;
    private String requestNumber;
    private String matName;
    private String matCode;
    private String umsNumber;

    //constructure region
    public ReturnedMaterialSearchedFormBean() {
    }

    public double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public int getMs2rId() {
        return ms2rId;
    }

    public void setMs2rId(int ms2rId) {
        this.ms2rId = ms2rId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getSto2Id() {
        return sto2Id;
    }

    public void setSto2Id(int sto2Id) {
        this.sto2Id = sto2Id;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getUmsNumber() {
        return umsNumber;
    }

    public void setUmsNumber(String umsNumber) {
        this.umsNumber = umsNumber;
    }
}
