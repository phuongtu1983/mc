/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.OsDDetailBean;

/**
 *
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class OsDMaterialFormBean extends org.apache.struts.action.ActionForm {

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
    private String discipline;
    private int reqDetailId;
    private String itemNo;
    private String[] nonConform;
    private String description;
    private int status;
    private String correctAct;
    private String vendorNote;
    private String closedOut;
    private String closedDate;
    private String remark;

    //constructure region
    public OsDMaterialFormBean() {
        super();
    }

    public OsDMaterialFormBean(OsDDetailBean bean) {
        super();
        this.detId = bean.getDetId(); // primary key
        this.osdId = bean.getOsdId();
        this.matId = bean.getMatId();
        this.matName = bean.getMatName();
        this.matCode = bean.getMatCode();
        this.unit = bean.getUnit();
        this.quantity = bean.getQuantity();
        this.reqId = bean.getReqId();
        this.reqNumber = bean.getReqNumber();
        this.proName = bean.getProName();
        this.reqDetailId = bean.getReqDetailId();
        this.itemNo = bean.getItemNo();
        if (!GenericValidator.isBlankOrNull(bean.getNonConform())) {
            this.nonConform = bean.getNonConform().split(",");
        }
        this.description = bean.getDescription();
        this.status = bean.getStatus();
        this.correctAct = bean.getCorrectAct();
        this.vendorNote = bean.getVendorNote();
        this.closedOut = bean.getClosedOut();
        this.closedDate = bean.getClosedDate();
        this.remark = bean.getRemark();
        this.discipline = bean.getDiscipline();
    }

    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedOut(String closedOut) {
        this.closedOut = closedOut;
    }

    public String getClosedOut() {
        return closedOut;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public String getCorrectAct() {
        return correctAct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getDetId() {
        return detId;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setNonConform(String[] nonConform) {
        this.nonConform = nonConform;
    }

    public String[] getNonConform() {
        return nonConform;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int getReqDetailId() {
        return reqDetailId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setVendorNote(String vendorNote) {
        this.vendorNote = vendorNote;
    }

    public String getVendorNote() {
        return vendorNote;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }
}


