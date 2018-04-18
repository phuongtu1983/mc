
package com.venus.mc.unit;

/**
 * @author Mai Vinh Loc
 */

import com.venus.mc.unit.*;

public class UnitFormBean extends org.apache.struts.action.ActionForm {

    private int uniId;
    private String unitVn;
    private String unitEn;

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

    public UnitFormBean() {
        super();
    
    }
}
