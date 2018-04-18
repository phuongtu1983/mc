/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comevalmaterial;

import com.venus.mc.comeval.*;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalMaterialFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    
    private int ceId; // primary key
    private int ccId; // foreign key : reference to com_clarification(cc_id)
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int borId;
    private String createdDate;
    private String[] cegId;
    private String[] regCegId;
    private String[] employee;
    private String[] position;
    private String[] evalId;
    private String[] evalEmployee;
    private String[] evalPosition;
    private String[] evalNote;
    private String tenNumber;
    private String borNumber;
    private String kind;
    private int[] cemId;

    //constructure region
    public ComEvalMaterialFormBean() {
        super();
   
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }

    public int getBorId() {
        return borId;
    }

    //properties region
    public int getCeId() {
        return this.ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public int getCcId() {
        return this.ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String[] getPosition() {
        return position;
    }

    public String[] getRegCegId() {
        return regCegId;
    }

    public String[] getCegId() {
        return cegId;
    }

    public String[] getEmployee() {
        return employee;
    }

    public void setEmployee(String[] employee) {
        this.employee = employee;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public void setRegCegId(String[] regTegId) {
        this.regCegId = regTegId;
    }

    public void setCegId(String[] tegId) {
        this.cegId = tegId;
    }

    public String getBorNumber() {
        return borNumber;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public void setBorNumber(String borNumber) {
        this.borNumber = borNumber;
    }

    public String getTenNumber() {
        return tenNumber;
    }

    public void setEvalPosition(String[] evalPosition) {
        this.evalPosition = evalPosition;
    }

    public void setEvalNote(String[] evalNote) {
        this.evalNote = evalNote;
    }

    public void setEvalId(String[] evalId) {
        this.evalId = evalId;
    }

    public void setEvalEmployee(String[] evalEmployee) {
        this.evalEmployee = evalEmployee;
    }

    public String[] getEvalPosition() {
        return evalPosition;
    }

    public String[] getEvalNote() {
        return evalNote;
    }

    public String[] getEvalId() {
        return evalId;
    }

    public String[] getEvalEmployee() {
        return evalEmployee;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setCemId(int[] cemId) {
        this.cemId = cemId;
    }

    public int[] getCemId() {
        return cemId;
    }

}
