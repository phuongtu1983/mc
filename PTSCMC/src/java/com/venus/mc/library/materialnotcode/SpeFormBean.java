
package com.venus.mc.library.materialnotcode;

/**
 * @author Mai Vinh Loc
 */

public class SpeFormBean extends org.apache.struts.action.ActionForm {

    private int spe1Id;
    private int spe2Id;
    private int spe3Id;
    private int spe4Id;
    private int spe5Id;
    private int spe6Id;
    private String sign;
    private String note;
    private String spe;

    public String getSign() {
        return sign;
    }

    public String getSpe() {
        return spe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }
        

    public int getSpe1Id() {
        return spe1Id;
    }

    public int getSpe2Id() {
        return spe2Id;
    }

    public int getSpe3Id() {
        return spe3Id;
    }

    public int getSpe4Id() {
        return spe4Id;
    }

    public int getSpe5Id() {
        return spe5Id;
    }

    public int getSpe6Id() {
        return spe6Id;
    }
   

    public void setSpe1Id(int spe1Id) {
        this.spe1Id = spe1Id;
    }

    public void setSpe2Id(int spe2Id) {
        this.spe2Id = spe2Id;
    }

    public void setSpe3Id(int spe3Id) {
        this.spe3Id = spe3Id;
    }

    public void setSpe4Id(int spe4Id) {
        this.spe4Id = spe4Id;
    }

    public void setSpe5Id(int spe5Id) {
        this.spe5Id = spe5Id;
    }

    public void setSpe6Id(int spe6Id) {
        this.spe6Id = spe6Id;
    }


    public SpeFormBean() {
        super();
   
    }
}
