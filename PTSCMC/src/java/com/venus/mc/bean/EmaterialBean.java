package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class EmaterialBean {

    private int ematId;
    private int oriId;
    private int oriIdEn;
    private int uniId;
    private int uniIdEn;
    private String code;
    private String nameVn;
    private String nameEn;
    private int kind;
    private String kindName;
    private String note;
    private String qc;

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

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public EmaterialBean() {
    }
    //1:CCDC ; 2: VTTB; 3: TSCĐ; 4: May moc; 5: Bao ho lao dong; 6: Phu tung
    public static int KIND_CCDC = 1;
    public static int KIND_VTTB = 2;
    public static int KIND_TSCD = 3;
    public static int KIND_MAYMOC = 4;
    public static int KIND_BAOHO = 5;
    public static int KIND_PHUTUNG = 6;
    //1: STRUCTURE; 2:PIPING; 3:MECHANICAL; 4:ELECTRICAL&INSTRUMENT
    public static int TYPE1 = 1;
    public static int TYPE2 = 2;
    public static int TYPE3 = 3;
    public static int TYPE4 = 4;
}
