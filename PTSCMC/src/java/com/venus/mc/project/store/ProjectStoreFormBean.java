/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.project.store;

/**
 *
 * @author kngo
 */
public class ProjectStoreFormBean extends org.apache.struts.action.ActionForm {
    private int stoId; // primary key
    private int proId; // foreign key : reference to project(pro_id)
    private String name;
    private String physicalAdd;
    private int kind;
    private String comments;

    //constructure region
    public ProjectStoreFormBean() {
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }
    
    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalAdd() {
        return this.physicalAdd;
    }

    public void setPhysicalAdd(String physicalAdd) {
        this.physicalAdd = physicalAdd;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
