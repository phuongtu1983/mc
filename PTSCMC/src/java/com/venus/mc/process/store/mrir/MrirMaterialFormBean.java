/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

/**
 *
 * @author thcao
 */
public class MrirMaterialFormBean extends org.apache.struts.action.ActionForm {

    private String detId;
    private String comment;
    private String matName;

    public MrirMaterialFormBean() {
        super();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getDetId() {
        return detId;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }
}
