package com.venus.mc.material;

/**
 * @author Mai Vinh Loc
 */
public class MaterialFormBean extends org.apache.struts.action.ActionForm {

    private int matId;
    private int oriId;
    private int oriIdEn;
    private int uniId;
    private int uniIdEn;
    private String code;
    private String nameVn;
    private String nameEn;
    private int kind;
    private int type;
    private int spe1Id;
    private int spe2Id;
    private int spe3Id;
    private int spe4Id;
    private int spe5Id;
    private int spe6Id;
    private String spe7;
    private String note;
    private int groId;
    private String qc;
    private String spe;
    private int reqId;
    private String deliveryTime;

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }

    public String getQc() {
        return qc;
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

    public int getGroId() {
        return groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public String getCode() {
        return code;
    }

    public int getKind() {
        return kind;
    }

    public int getMatId() {
        return matId;
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

    public String getSpe7() {
        return spe7;
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

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public void setSpe1Id(int spe1Id) {
        this.spe1Id = spe1Id;
    }

    public void setSpe2Id(int spe2Id) {
        this.spe2Id = spe2Id;
    }

    public void setOriId(int oriId) {
        this.oriId = oriId;
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

    public void setSpe7(String spe7) {
        this.spe7 = spe7;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public MaterialFormBean() {
        super();

    }
}
