/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.position;

/**
 *
 * @author kngo
 */
public class SearchAdvPositionFormBean extends org.apache.struts.action.ActionForm {
    private int posId; // primary key
    private String name;

    public SearchAdvPositionFormBean() {
    }

    public int getPosId() {
        return this.posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
