
package com.venus.mc.origin;

/**
 * @author Mai Vinh Loc
 */

public class OriginFormBean extends org.apache.struts.action.ActionForm {

    private int oriId;
    private String nameVn;
    private String nameEn;
    private String note;
    
    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setOriId(int oriId) {
        this.oriId = oriId;
    }

    public int getOriId() {
        return oriId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameVn() {
        return nameVn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OriginFormBean() {
        super();
    
    }
}
