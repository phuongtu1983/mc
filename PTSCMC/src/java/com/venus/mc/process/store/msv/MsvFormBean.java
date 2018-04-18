/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.msv;

import com.venus.mc.bean.MsvBean;

/**
 *
 * @author thcao
 */
public class MsvFormBean extends org.apache.struts.action.ActionForm {

    public static final int TYPE_MSV = 0;
    public static final int TYPE_MRV = 1;
    public static final int TYPE_DMV = 2;
    private int msvId;
    private String msvNumber;
    private String createdDate;
    private String description;
    private int createdEmpId;
    private String createdEmpName;
    private int stoId;
    private String stoName;
    private int mrirId;
    private String mrirNumber;
    private int conId;
    private String conNumber;
    private String deliverer;
    private double total;
    private int kind;
    private int type;
    private int deliveryEmpId;
    private String deliveryEmpName;
    private int orgId;
    private String orgName;
    private int proId;
    private String proName;
    private String mivNumbers;
    private int receiveEmpId;
    private String receiveEmpName;
    private String dmvOrder;
    private String vendorName;
    private int[] matId;
    private int[] reqDetailId;
    private String[] unit;
    private String[] quantity;
    private String[] price;

    public MsvFormBean() {
        super();
    }

    public MsvFormBean(MsvBean bean) {
        this.msvId = bean.getMsvId();
        this.msvNumber = bean.getMsvNumber();
        this.createdDate = bean.getCreatedDate();
        this.description = bean.getDescription();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.stoId = bean.getStoId();
        this.stoName = bean.getStoName();
        this.mrirId = bean.getMrirId();
        this.mrirNumber = bean.getMrirNumber();
        this.conId = bean.getConId();
        this.conNumber = bean.getConNumber();
        this.total = bean.getTotal();
        this.kind = bean.getKind();
        this.deliverer = bean.getDeliverer();
        this.type = bean.getType();
        this.deliveryEmpId = bean.getDeliveryEmpId();
        this.deliveryEmpName = bean.getDeliveryEmpName();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
        this.proId = bean.getProId();
        this.proName = bean.getProName();
        this.mivNumbers = bean.getMivNumbers();
        this.receiveEmpId = bean.getReceiveEmpId();
        this.receiveEmpName = bean.getReceiveEmpName();
        this.dmvOrder = bean.getDmvOrder();
        this.vendorName = bean.getVendorName();
    }

    public int getMsvId() {
        return msvId;
    }

    public void setMsvId(int msvId) {
        this.msvId = msvId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public String getMsvNumber() {
        return msvNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
    }

    public void setDescription(String content) {
        this.description = content;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getStoId() {
        return stoId;
    }

    public int getMrirId() {
        return mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getConId() {
        return conId;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getMrirNumber() {
        return mrirNumber;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getStoName() {
        return stoName;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public int[] getMatId() {
        return matId;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getPrice() {
        return price;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setDeliveryEmpId(int deliveryEmpId) {
        this.deliveryEmpId = deliveryEmpId;
    }

    public int getDeliveryEmpId() {
        return deliveryEmpId;
    }

    public void setDeliveryEmpName(String deliveryEmpName) {
        this.deliveryEmpName = deliveryEmpName;
    }

    public String getDeliveryEmpName() {
        return deliveryEmpName;
    }

    public void setDmvOrder(String dmvOrder) {
        this.dmvOrder = dmvOrder;
    }

    public String getDmvOrder() {
        return dmvOrder;
    }

    public void setMivNumbers(String mivNumbers) {
        this.mivNumbers = mivNumbers;
    }

    public String getMivNumbers() {
        return mivNumbers;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setReceiveEmpId(int receiveEmpId) {
        this.receiveEmpId = receiveEmpId;
    }

    public int getReceiveEmpId() {
        return receiveEmpId;
    }

    public void setReceiveEmpName(String receiveEmpName) {
        this.receiveEmpName = receiveEmpName;
    }

    public String getReceiveEmpName() {
        return receiveEmpName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getConNumber() {
        return conNumber;
    }
}
