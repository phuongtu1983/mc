package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */

public class UnitBean {

    private int uniId;
    private String unitVn;
    private String unitEn;
    
    /** Creates a new instance of MemberBean */
    public UnitBean() {
    }

    public void setUnitVn(String unitVn) {
        this.unitVn = unitVn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public int getUniId() {
        return uniId;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public String getUnitVn() {
        return unitVn;
    }
    
}
