package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class MaterialBean {

    private int matId;
    private int oriId;
    private int oriIdEn;
    private int uniId;
    private int uniIdEn;
    private String code;
    private String nameVn;
    private String nameEn;
    private int type;
    private int kind;
    private String kindName;
    private int spe1Id;
    private int spe2Id;
    private int spe3Id;
    private int spe4Id;
    private int spe5Id;
    private int spe6Id;
    private String spe7;
    private String note;
    private int groId;
    private String groName;//phuongtu
    private String unitName;//phuongtu
    private String typeName;
    private String qc;
    private String spe;
    private String requestNumber;
    private String empName;
    private String orgName;
    private String contractNumber;
    private String deliveryTime;
    private int reqId;

    public MaterialBean() {
    }

    public MaterialBean(int matId) {
        this.matId = matId;
    }

    public MaterialBean(int matId, int oriId, int oriIdEn, int uniId, int uniIdEn, String code, String nameVn, String nameEn, int type, int kind, String kindName, int spe1Id, int spe2Id, int spe3Id, int spe4Id, int spe5Id, int spe6Id, String spe7, String note, int groId, String groName, String unitName, String typeName, String qc, String spe, String requestNumber, String empName, String orgName) {
        this.matId = matId;
        this.oriId = oriId;
        this.oriIdEn = oriIdEn;
        this.uniId = uniId;
        this.uniIdEn = uniIdEn;
        this.code = code;
        this.nameVn = nameVn;
        this.nameEn = nameEn;
        this.type = type;
        this.kind = kind;
        this.kindName = kindName;
        this.spe1Id = spe1Id;
        this.spe2Id = spe2Id;
        this.spe3Id = spe3Id;
        this.spe4Id = spe4Id;
        this.spe5Id = spe5Id;
        this.spe6Id = spe6Id;
        this.spe7 = spe7;
        this.note = note;
        this.groId = groId;
        this.groName = groName;
        this.unitName = unitName;
        this.typeName = typeName;
        this.qc = qc;
        this.spe = spe;
        this.requestNumber = requestNumber;
        this.empName = empName;
        this.orgName = orgName;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }

    public String getSpe() {
        return spe;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }

    public String getQc() {
        return qc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public static int KIND_STRUCTURE = 1;
    public static int KIND_PIPING = 2;
    public static int KIND_MECHANICAL = 3;
    public static int KIND_ELEC_INS = 4;
}
