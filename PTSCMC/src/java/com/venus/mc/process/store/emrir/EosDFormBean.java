/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.EosDBean;

/**
 *
 * @author kngo
 */
public class EosDFormBean extends org.apache.struts.action.ActionForm {

    private int eosdId;
    private int emrirId;
    private int ednId;
    private String createdDate;
    private String eosdNumber;
    private String econNumber;
    private int createdEmpId;
    private String createdEmpName;
    private String description;
    private String correctAct;
    private int actionBy;
    private String dueDate;
    private int closed;
    private String closedDate;
    private String note;
    private int cbxMaterialOfDn;
    private String[] chkDetId;
    private String[] detId;
    private String[] nonConform;
    private int[] ematId;
    private String[] unit;
    private String[] quantity;

    //constructure region
    public EosDFormBean() {
        super();
    }

    public EosDFormBean(EosDBean bean) {
        this.eosdId = bean.getEosdId();
        this.createdDate = bean.getCreatedDate();
        this.eosdNumber = bean.getEosdNumber();
        this.emrirId = bean.getEmrirId();
        this.createdEmpId = bean.getCreatedEmpId();
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
    }

    //properties region
    public int getEosdId() {
        return this.eosdId;
    }

    public void setEosdId(int eosdId) {
        this.eosdId = eosdId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEosdNumber() {
        return this.eosdNumber;
    }

    public void setEosdNumber(String eosdNumber) {
        this.eosdNumber = eosdNumber;
    }

    public String getEconNumber() {
        return this.econNumber;
    }

    public void setEconNumber(String econNumber) {
        this.econNumber = econNumber;
    }

    public int getEmrirId() {
        return this.emrirId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
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

    public void setEmatId(int[] ematId) {
        this.ematId = ematId;
    }

    public int[] getEmatId() {
        return ematId;
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

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public void setCbxMaterialOfDn(int cbxMaterialOfDn) {
        this.cbxMaterialOfDn = cbxMaterialOfDn;
    }

    public int getCbxMaterialOfDn() {
        return cbxMaterialOfDn;
    }

    public void setChkDetId(String[] chkDetId) {
        this.chkDetId = chkDetId;
    }

    public String[] getChkDetId() {
        return chkDetId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public int getEdnId() {
        return ednId;
    }
}
