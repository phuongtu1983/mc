/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class OsDDetailBean {

    private int no; //for report
    private String detId; // primary key
    private int osdId;
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;
    private int reqId;
    private String reqNumber;
    private String proName;
    private int reqDetailId;
    private String itemNo;
    private String discipline;
    private String nonConform;
    private String nonConformText;
    private String description;
    private int status;
    private String statusText;
    private String correctAct;
    private String vendorNote;
    private String closedOut;
    private String closedDate;
    private String remark;
    private int stt;
    private int matIdTemp;
    private String matNameTemp;
    private String unitTemp;

    //constructure region
    public OsDDetailBean() {
    }


    //properties region
    public String getDetId() {
        return this.detId;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public void setMatIdTemp(int matIdTemp) {
        this.matIdTemp = matIdTemp;
    }

    public int getMatIdTemp() {
        return matIdTemp;
    }

    public String getUnitTemp() {
        return unitTemp;
    }

    public String getMatNameTemp() {
        return matNameTemp;
    }

    public void setMatNameTemp(String matNameTemp) {
        this.matNameTemp = matNameTemp;
    }

    public void setUnitTemp(String unitTemp) {
        this.unitTemp = unitTemp;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return this.matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public String getCorrectAct() {
        return correctAct;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNonConform(String nonConform) {
        this.nonConform = nonConform;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getNonConform() {
        return nonConform;
    }

    public void setVendorNote(String vendorNote) {
        this.vendorNote = vendorNote;
    }

    public String getVendorNote() {
        return vendorNote;
    }

    public void setClosedOut(String closedOut) {
        this.closedOut = closedOut;
    }

    public String getClosedOut() {
        return closedOut;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setNonConformText(String nonConformText) {
        this.nonConformText = nonConformText;
    }

    public String getNonConformText() {
        return nonConformText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
}

