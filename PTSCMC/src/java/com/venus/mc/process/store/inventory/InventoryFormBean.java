/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.inventory;

import com.venus.mc.bean.InventoryBean;

/**
 *
 * @author kngo
 */
public class InventoryFormBean extends org.apache.struts.action.ActionForm {

    private int invId; // primary key
    private int stoId; // foreign key : reference to store(sto_id)
    private String invDate;
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String comment;
    private String[] quantityActual;
    private String[] quantityDocument;
    private String[] commentDetail;
    private String[] material;

    //constructure region
    public InventoryFormBean() {
        super();
    }

    public InventoryFormBean(InventoryBean bean) {
        this.invId = bean.getInvId();
        this.stoId = bean.getStoId();
        this.invDate = bean.getInvDate();
        this.createdEmp = bean.getCreatedEmp();
        this.comment = bean.getComment();
    }

    //properties region
    public int getInvId() {
        return this.invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public String getInvDate() {
        return this.invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int creator) {
        this.createdEmp = creator;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getMaterial() {
        return material;
    }

    public void setMaterial(String[] material) {
        this.material = material;
    }

    public String[] getQuantityActual() {
        return quantityActual;
    }

    public void setQuantityActual(String[] quantityActual) {
        this.quantityActual = quantityActual;
    }

    public String[] getQuantityDocument() {
        return quantityDocument;
    }

    public void setQuantityDocument(String[] quantityDocument) {
        this.quantityDocument = quantityDocument;
    }

    public String[] getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String[] commentDetail) {
        this.commentDetail = commentDetail;
    }
}
