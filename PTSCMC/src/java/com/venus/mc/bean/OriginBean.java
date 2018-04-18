package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */

public class OriginBean {

    private int oriId;
    private String nameVn;
    private String nameEn;
    private String note;
    
    public OriginBean() {
    }

    public OriginBean( int oriId, String name, String note){
		this.oriId = oriId;
		this.nameVn = name;
		this.note = note;
	}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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
    
}
