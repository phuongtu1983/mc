/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.OsDBean;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class OsDFormBean extends org.apache.struts.action.ActionForm {

    private int osdId;
    private int mrirId;
    private String createdDate;
    private String osdNumber;
    private String conNumber;
    private String reqNumber;
    private String venName;
    private int dnId;
    private String dnNumber;
    private int createdEmpId;
    private String createdEmpName;
    private String[] nonConform;
    private String description;
    private String correctAct;
    private int actionBy;
    private String dueDate;
    private int closed;
    private String closedDate;
    private String note;
    private int[] reqDetailId;
    private int[] matId;
    private String[] unit;
    private String[] quantity;
    private int reqId;
    private int cbxMaterialOfReq;
    private String[] chkDetId;
    private String[] detId;

    //constructure region
    public OsDFormBean() {
        super();
    }

    public OsDFormBean(OsDBean bean) {
        this.osdId = bean.getOsdId();
        this.createdDate = bean.getCreatedDate();
        this.osdNumber = bean.getOsdNumber();
        this.mrirId = bean.getMrirId();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();

        if (!GenericValidator.isBlankOrNull(bean.getNonConform())) {
            this.nonConform = bean.getNonConform().split(",");
        }

        this.description = bean.getDescription();
        this.correctAct = bean.getCorrectAct();
        this.actionBy = bean.getActionBy();
        this.dueDate = bean.getDueDate();
        this.closed = bean.getClosed();
        this.closedDate = bean.getClosedDate();
        this.note = bean.getNote();
        this.dnId = bean.getDnId();
        this.dnNumber = bean.getDnNumber();
    }

    //properties region
    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOsdNumber() {
        return this.osdNumber;
    }

    public void setOsdNumber(String osdNumber) {
        this.osdNumber = osdNumber;
    }

    public String getConNumber() {
        return this.conNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getReqNumber() {
        return this.reqNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getVenName() {
        return this.venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public int getMrirId() {
        return this.mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public String getCreatedEmpName() {
        return this.createdEmpName;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String[] getNonConform() {
        return this.nonConform;
    }

    public void setNonConform(String[] nonConform) {
        this.nonConform = nonConform;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAct() {
        return this.correctAct;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public int getActionBy() {
        return this.actionBy;
    }

    public void setActionBy(int actionBy) {
        this.actionBy = actionBy;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getClosed() {
        return this.closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public String getClosedDate() {
        return this.closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int[] getMatId() {
        return matId;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public int getDnId() {
        return dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public int getCbxMaterialOfReq() {
        return cbxMaterialOfReq;
    }

    public void setCbxMaterialOfReq(int cbxMaterialOfReq) {
        this.cbxMaterialOfReq = cbxMaterialOfReq;
    }

    public void setChkDetId(String[] chkDetId) {
        this.chkDetId = chkDetId;
    }

    public String[] getChkDetId() {
        return chkDetId;
    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }
}


