/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TechEvalDetailBean {

    //fields region
    private int detId; // primary key
    private int terId; // foreign key : reference to tech_eval_vendor(ter_id)
    private int matId; // foreign key : reference to material(mat_id)
    private int tenId;
    private String propose;
    private String deliveryTime;
    private String deliveryTimeVendor;
    private String provideDate;
    private String otherCondition;
    private String offer;
    private String spec;
    private String qty;
    private String unit;
    private int reqDetailId;
    private int evalTbe;
    private String nameVn;
    private String certificateAttach;
    private String vendorName;
    private String result;
    private String stt;
    private int venId;
    private int kind;
    private String evalTbeString;
    private int confirm;
    private String nameTemp;
    //phuongtu
    private String projectName;
    private int detIdTp;
    private String unitTemp;
    private int cb;

    //constructure region
    public TechEvalDetailBean() {
    }

    public TechEvalDetailBean(int detId) {
        this.detId = detId;
    }

    public TechEvalDetailBean(int detId, int terId, int matId, int tenId, String propose, String deliveryTime, String provideDate, String otherCondition, String offer, String spec, String qty, String unit, int reqDetailId, int evalTbe, String nameVn, String certificateAttach, String vendorName, String result, String stt, int venId, int kind, String evalTbeString, int confirm, String projectName, int detIdTp) {
        this.detId = detId;
        this.terId = terId;
        this.matId = matId;
        this.tenId = tenId;
        this.propose = propose;
        this.deliveryTime = deliveryTime;
        this.provideDate = provideDate;
        this.otherCondition = otherCondition;
        this.offer = offer;
        this.spec = spec;
        this.qty = qty;
        this.unit = unit;
        this.reqDetailId = reqDetailId;
        this.evalTbe = evalTbe;
        this.nameVn = nameVn;
        this.certificateAttach = certificateAttach;
        this.vendorName = vendorName;
        this.result = result;
        this.stt = stt;
        this.venId = venId;
        this.kind = kind;
        this.evalTbeString = evalTbeString;
        this.confirm = confirm;
        this.projectName = projectName;
        this.detIdTp = detIdTp;

    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public void setNameTemp(String nameTemp) {
        this.nameTemp = nameTemp;
    }

    public String getNameTemp() {
        return nameTemp;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getTerId() {
        return this.terId;
    }

    public void setTerId(int terId) {
        this.terId = terId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getPropose() {
        return this.propose;
    }

    public void setPropose(String propose) {
        this.propose = propose;
    }

    public String getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOtherCondition() {
        return this.otherCondition;
    }

    public void setOtherCondition(String otherCondition) {
        this.otherCondition = otherCondition;
    }

    public String getOffer() {
        return this.offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getSpec() {
        return this.spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getEvalTbe() {
        return this.evalTbe;
    }

    public void setEvalTbe(int evalTbe) {
        this.evalTbe = evalTbe;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public String getNameVn() {
        return nameVn;
    }

    public String getProvideDate() {
        return provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public String getCertificateAttach() {
        return certificateAttach;
    }

    public void setCertificateAttach(String certificateAttach) {
        this.certificateAttach = certificateAttach;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getResult() {
        return result;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getStt() {
        return stt;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTenId() {
        return tenId;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public String getEvalTbeString() {
        return evalTbeString;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setEvalTbeString(String evalTbeString) {
        this.evalTbeString = evalTbeString;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getDetIdTp() {
        return detIdTp;
    }

    public void setDetIdTp(int detIdTp) {
        this.detIdTp = detIdTp;
    }

    public String getDeliveryTimeVendor() {
        return deliveryTimeVendor;
    }

    public void setDeliveryTimeVendor(String deliveryTimeVendor) {
        this.deliveryTimeVendor = deliveryTimeVendor;
    }

    public String getUnitTemp() {
        return unitTemp;
    }

    public void setUnitTemp(String unitTemp) {
        this.unitTemp = unitTemp;
    }
    public static int RESULT_NOTEVAL = 0;
    public static int RESULT_REACH = 1;
    public static int RESULT_NOTREACH = 2;
}
