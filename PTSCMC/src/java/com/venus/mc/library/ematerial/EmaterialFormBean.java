
package com.venus.mc.library.ematerial;

import com.venus.mc.bean.EmaterialBean;

/**
 * @author Mai Vinh Loc
 */

public class EmaterialFormBean extends org.apache.struts.action.ActionForm {

    private int ematId;
    private int oriId;
    private int oriIdEn;
    private int uniId;
    private int uniIdEn;
    private String code;
    private String nameVn;
    private String nameEn;
    private int kind;
    private String note;
    private String qc;

    public EmaterialFormBean (EmaterialBean bean) {
        this.ematId = bean.getEmatId();
        this.uniId = bean.getUniId();
        this.nameVn = bean.getNameVn();
    }

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }
    
    public int getUniIdEn() {
        return uniIdEn;
    }

    public void setUniIdEn(int uniIdEn) {
        this.uniIdEn = uniIdEn;
    }

    public int getOriIdEn() {
        return oriIdEn;
    }

    public void setOriIdEn(int oriIdEn) {
        this.oriIdEn = oriIdEn;
    }

    public String getCode() {
        return code;
    }

    public int getKind() {
        return kind;
    }

    public int getEmatId() {
        return ematId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameVn() {
        return nameVn;
    }

    public int getOriId() {
        return oriId;
    }

    public int getUniId() {
        return uniId;
    }

    public String getNote() {
        return note;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }
 
    public void setOriId(int oriId) {
        this.oriId = oriId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    public EmaterialFormBean() {
        super();
    
    }
}
