/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

/**
 *
 * @author mai vinh loc
 */
public class TechEvalDetailFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    
    private int[] detId; // primary key
    private int terId; // foreign key : reference to tech_eval_vendor(ter_id)
    private int matId; // foreign key : reference to material(mat_id)
    private int tenId;
    private String propose;
    private String[] deliveryTime;
    private String provideDate;
    private String[] otherCondition;
    private String offer;
    private String[] spec;
    private String[] qty;
    private String[] unit;
    private int[] evalTbe;
    private int[] reqDetailId;
    private String nameVn;
    private String certificateAttach;
    private String result;
    private int createdEmp;

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }
    
    public int[] getDetId() {
        return this.detId;
    }

    public void setDetId(int[] detId) {
        this.detId = detId;
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

    public String[] getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(String[] deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String[] getOtherCondition() {
        return this.otherCondition;
    }

    public void setOtherCondition(String[] otherCondition) {
        this.otherCondition = otherCondition;
    }

    public String getOffer() {
        return this.offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String[] getSpec() {
        return this.spec;
    }

    public void setSpec(String[] spec) {
        this.spec = spec;
    }

    public String[] getQty() {
        return this.qty;
    }

    public void setQty(String[] qty) {
        this.qty = qty;
    }

    public String[] getUnit() {
        return this.unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public int[] getEvalTbe() {
        return this.evalTbe;
    }

    public void setEvalTbe(int[] evalTbe) {
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

     public TechEvalDetailFormBean() {
        super();

    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTenId() {
        return tenId;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

}
