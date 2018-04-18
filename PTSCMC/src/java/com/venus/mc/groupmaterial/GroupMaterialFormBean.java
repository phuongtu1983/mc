/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.groupmaterial;

/**
 *
 * @author kngo
 */
public class GroupMaterialFormBean extends org.apache.struts.action.ActionForm {

    private int groId; // primary key
    private String name;
    private String note;

    //constructure region
    public GroupMaterialFormBean() {
    }

    //properties region
    public int getGroId() {
        return this.groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
