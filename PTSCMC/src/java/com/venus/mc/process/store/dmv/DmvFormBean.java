/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv;

import com.venus.mc.bean.DmvBean;
import org.apache.struts.upload.MultipartRequestHandler;

/**
 *
 * @author thcao
 */
public class DmvFormBean extends org.apache.struts.action.ActionForm {

    private int dmvId;
    private String createdDate;
    private int createdEmpId;
    private String createdEmpName;
    private String dmvNumber;
    private String deliverer;
    private int reqId;
    private String reqNumber;
    private int conId;
    private String conNumber;
    private String vendorName;
    private int dnId;
    private int receiveEmpId;
    private String receiveEmpName;
    private int orgId;
    private String orgName;
    private String dmvOrder;
    private String description;
    private int stoId;
    private String stoName;
    private int proId;
    private String proName;
    private double total;
    private int kind;
    private int msvId;
    private String msvNumber;
    private int mivId;
    private String mivNumber;
    private int mrirId;
    private String mrirNumber;
    private int rfmId;
    private String rfmNumber;
    private int[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] price;
    private int[] reqDetailId;

    public DmvFormBean() {
        super();
    }

    public DmvFormBean(DmvBean bean) {
        this.dmvId = bean.getDmvId();
        this.createdDate = bean.getCreatedDate();
        this.dmvNumber = bean.getDmvNumber();
        this.deliverer = bean.getDeliverer();
        this.conId = bean.getConId();
        this.conNumber = bean.getConNumber();
        this.dnId = bean.getDnId();
        this.receiveEmpId = bean.getReceiveEmpId();
        this.receiveEmpName = bean.getReceiveEmpName();
        this.vendorName = bean.getVendorName();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
        this.dmvOrder = bean.getDmvOrder();
        this.description = bean.getDescription();
        this.stoId = bean.getStoId();
        this.stoName = bean.getStoName();
        this.total = bean.getTotal();
        this.kind = bean.getKind();
        this.msvId = bean.getMsvId();
        this.msvNumber = bean.getMsvNumber();
        this.mivId = bean.getMivId();
        this.mivNumber = bean.getMivNumber();
        this.mrirId = bean.getMrirId();
        this.mrirNumber = bean.getMrirNumber();
        this.rfmId = bean.getRfmId();
        this.rfmNumber = bean.getRfmNumber();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdEmpName = bean.getCreatedEmpName();
        this.reqId = bean.getReqId();
        this.reqNumber = bean.getReqNumber();
        this.proId = bean.getProId();
        this.proName = bean.getProName();
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getConId() {
        return conId;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDmvId(int dmvId) {
        this.dmvId = dmvId;
    }

    public int getDmvId() {
        return dmvId;
    }

    public void setDmvNumber(String dmvNumber) {
        this.dmvNumber = dmvNumber;
    }

    public String getDmvNumber() {
        return dmvNumber;
    }

    public int getDnId() {
        return dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setDmvOrder(String dmvOrder) {
        this.dmvOrder = dmvOrder;
    }

    public String getDmvOrder() {
        return dmvOrder;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public int getMivId() {
        return mivId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public int getMrirId() {
        return mrirId;
    }

    public void setMsvId(int msvId) {
        this.msvId = msvId;
    }

    public int getMsvId() {
        return msvId;
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

    public int getRfmId() {
        return rfmId;
    }

    public void setRfmId(int rfmId) {
        this.rfmId = rfmId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getStoId() {
        return stoId;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getStoName() {
        return stoName;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
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

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
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

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getMrirNumber() {
        return mrirNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
    }

    public String getMsvNumber() {
        return msvNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public String getRfmNumber() {
        return rfmNumber;
    }
}
