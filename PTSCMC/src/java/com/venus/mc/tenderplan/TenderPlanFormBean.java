
/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.tenderplan;

import com.venus.mc.bean.TenderPlanBean;

public class TenderPlanFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int tenId;
    private int createdEmp;
    private String tenderNumber;
    private String createdDate;
    private String packName;
    private String subject;
    private String foundation;
    private String supplyScope;
    private String form;
    private int offerType;
    private String financial;
    private String bidSendingDate;
    private String bidDeadline;
    private String bidOpeningDate;
    private String evaluatedDate;
    private String approvedDate;
    private String contractDate;
    private String contractExecDate;
    private String bidSendingTime;
    private String bidDeadlineTime;
    private String bidOpeningTime;
    private String evaluatedTime;
    private String approvedTime;
    private String contractTime;
    private String contractExecTime;
    private String techEvalStandard;
    private String comEvalStandard;
    private int evalKind;
    private int handleEmp;
    private String deliveryTime;
//    private int cerId;
    private double financialPreTax;
    private String[] vendor;
    private String[] evalId;
    private String[] evalEmployee;
    private String[] evalPosition;
    private String[] evalNote;
    private String[] tenDetId;
    private String[] matId;
    private String[] matUnit;
    private String[] matQuantity;
    private String[] matProvideDate;
    private String[] reqDetId;
    private String[] matPrice;
    private String[] matTotal;
    private String[] tcId;
    private String[] cerId;

    //constructure region
    public TenderPlanFormBean() {
        super();
    }

    public TenderPlanFormBean(TenderPlanBean bean) {
        this.tenId = bean.getTenId();
        this.tenderNumber = bean.getTenderNumber();
        this.createdDate = bean.getCreatedDate();
        this.packName = bean.getPackName();
        this.subject = bean.getSubject();
        this.foundation = bean.getFoundation();
        this.supplyScope = bean.getSupplyScope();
        this.form = bean.getForm();
        this.offerType = bean.getOfferType();
        this.financial = bean.getFinancial();
        this.bidSendingDate = bean.getBidSendingDate();
        this.bidDeadline = bean.getBidDeadline();
        this.bidOpeningDate = bean.getBidOpeningDate();
        this.evaluatedDate = bean.getEvaluatedDate();
        this.approvedDate = bean.getApprovedDate();
        this.contractDate = bean.getContractDate();
        this.contractExecDate = bean.getContractExecDate();
        this.techEvalStandard = bean.getTechEvalStandard();
        this.comEvalStandard = bean.getComEvalStandard();
        this.evalKind = bean.getEvalKind();
        this.handleEmp = bean.getHandleEmp();
        this.bidSendingTime = bean.getBidSendingTime();
        this.bidDeadlineTime = bean.getBidDeadlineTime();
        this.bidOpeningTime = bean.getBidOpeningTime();
        this.evaluatedTime = bean.getEvaluatedTime();
        this.approvedTime = bean.getApprovedTime();
        this.contractTime = bean.getContractTime();
        this.contractExecTime = bean.getContractExecTime();
//        this.cerId = bean.getCerId();
        this.deliveryTime = bean.getDeliveryTime();
        this.financialPreTax = bean.getFinancialPreTax();
        this.createdEmp = bean.getCreatedEmp();
    }

    //properties region
    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getTenderNumber() {
        return this.tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPackName() {
        return this.packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFoundation() {
        return this.foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getSupplyScope() {
        return this.supplyScope;
    }

    public void setSupplyScope(String supplyScope) {
        this.supplyScope = supplyScope;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getOfferType() {
        return this.offerType;
    }

    public void setOfferType(int offerType) {
        this.offerType = offerType;
    }

    public String getFinancial() {
        return this.financial;
    }

    public void setFinancial(String financial) {
        this.financial = financial;
    }

    public String getBidSendingDate() {
        return this.bidSendingDate;
    }

    public void setBidSendingDate(String bidSendingDate) {
        this.bidSendingDate = bidSendingDate;
    }

    public String getBidDeadline() {
        return this.bidDeadline;
    }

    public void setBidDeadline(String bidDeadline) {
        this.bidDeadline = bidDeadline;
    }

    public String getBidOpeningDate() {
        return this.bidOpeningDate;
    }

    public void setBidOpeningDate(String bidOpeningDate) {
        this.bidOpeningDate = bidOpeningDate;
    }

    public String getEvaluatedDate() {
        return this.evaluatedDate;
    }

    public void setEvaluatedDate(String evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public String getApprovedDate() {
        return this.approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getContractDate() {
        return this.contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractExecDate() {
        return this.contractExecDate;
    }

    public void setContractExecDate(String contractExecDate) {
        this.contractExecDate = contractExecDate;
    }

    public String getTechEvalStandard() {
        return this.techEvalStandard;
    }

    public void setTechEvalStandard(String techEvalStandard) {
        this.techEvalStandard = techEvalStandard;
    }

    public String getComEvalStandard() {
        return this.comEvalStandard;
    }

    public void setComEvalStandard(String comEvalStandard) {
        this.comEvalStandard = comEvalStandard;
    }

    public String[] getVendor() {
        return vendor;
    }

    public void setVendor(String[] vendor) {
        this.vendor = vendor;
    }

    public String[] getEvalId() {
        return evalId;
    }

    public void setEvalId(String[] evalId) {
        this.evalId = evalId;
    }

    public String[] getEvalEmployee() {
        return evalEmployee;
    }

    public void setEvalEmployee(String[] evalEmployee) {
        this.evalEmployee = evalEmployee;
    }

    public String[] getEvalPosition() {
        return evalPosition;
    }

    public void setEvalPosition(String[] evalPosition) {
        this.evalPosition = evalPosition;
    }

    public String[] getEvalNote() {
        return evalNote;
    }

    public void setEvalNote(String[] evalNote) {
        this.evalNote = evalNote;
    }

    public String[] getTenDetId() {
        return tenDetId;
    }

    public void setTenDetId(String[] tenDetId) {
        this.tenDetId = tenDetId;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String[] matUnit) {
        this.matUnit = matUnit;
    }

    public String[] getMatQuantity() {
        return matQuantity;
    }

    public void setMatQuantity(String[] matQuantity) {
        this.matQuantity = matQuantity;
    }

    public String[] getMatProvideDate() {
        return matProvideDate;
    }

    public void setMatProvideDate(String[] matProvideDate) {
        this.matProvideDate = matProvideDate;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public int getEvalKind() {
        return evalKind;
    }

    public void setEvalKind(int evalKind) {
        this.evalKind = evalKind;
    }

    public int getHandleEmp() {
        return handleEmp;
    }

    public void setHandleEmp(int handleEmp) {
        this.handleEmp = handleEmp;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getBidDeadlineTime() {
        return bidDeadlineTime;
    }

    public void setBidDeadlineTime(String bidDeadlineTime) {
        this.bidDeadlineTime = bidDeadlineTime;
    }

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getBidSendingTime() {
        return bidSendingTime;
    }

    public void setBidSendingTime(String bidSendingTime) {
        this.bidSendingTime = bidSendingTime;
    }

    public String getContractExecTime() {
        return contractExecTime;
    }

    public void setContractExecTime(String contractExecTime) {
        this.contractExecTime = contractExecTime;
    }

    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
    }

    public String getEvaluatedTime() {
        return evaluatedTime;
    }

    public void setEvaluatedTime(String evaluatedTime) {
        this.evaluatedTime = evaluatedTime;
    }

//    public int getCerId() {
//        return cerId;
//    }
//
//    public void setCerId(int cerId) {
//        this.cerId = cerId;
//    }
    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getFinancialPreTax() {
        return financialPreTax;
    }

    public void setFinancialPreTax(double financialPreTax) {
        this.financialPreTax = financialPreTax;
    }

    public String[] getMatPrice() {
        return matPrice;
    }

    public void setMatPrice(String[] matPrice) {
        this.matPrice = matPrice;
    }

    public String[] getMatTotal() {
        return matTotal;
    }

    public void setMatTotal(String[] matTotal) {
        this.matTotal = matTotal;
    }

    public String[] getCerId() {
        return cerId;
    }

    public void setCerId(String[] cerId) {
        this.cerId = cerId;
    }

    public String[] getTcId() {
        return tcId;
    }

    public void setTcId(String[] tcId) {
        this.tcId = tcId;
    }
    public static int FORM_PRIVATE = 1;
    public static int FORM_FAX = 2;
    public static int OFFER_IN = 1;
    public static int OFFER_OUT = 2;
    public static int OFFER_INOUT = 3;
    public static int EVAL_KIND_ALL = 0;
    public static int EVAL_KIND_PART = 1;
}
