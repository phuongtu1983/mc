/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.bean;

public class TenderPlanBean {

    //fields region
    private int tenId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String tenderNumber;
    private String createdDate;
    private String tenderLetterDate;
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
    private String requestNumber;
    private String requestDate;
    private String ros;
    private int handleEmp;
    private String handleEmpName;
    private int handledStatus;
    private String status;
    private String deliveryTime;
    private int cerId;
    private double financialPreTax;
    private int IsNeedHighLight;
    private String cerText;
    private String currency;
    private String proName;
    private String orgName;
    private String orgNameUpcase;
    private String offerTypeStr;
    private int stt;
    private String day;
    private String month;
    private String year;
    private String abbreviate;
    private String packNameUpcase;
    private String color;
    //constructure region

    public TenderPlanBean() {
    }

    public TenderPlanBean(int tenId) {
        this.tenId = tenId;
    }

    public TenderPlanBean(int tenId, String tenderNumber, String createdDate, String packName, String subject, String foundation, String supplyScope, String form, int offerType, String financial, String bidSendingDate, String bidDeadline, String bidOpeningDate, String evaluatedDate, String approvedDate, String contractDate, String contractExecDate, String techEvalStandard, String comEvalStandard, int evalKind) {
        this.tenId = tenId;
        this.tenderNumber = tenderNumber;
        this.createdDate = createdDate;
        this.packName = packName;
        this.subject = subject;
        this.foundation = foundation;
        this.supplyScope = supplyScope;
        this.form = form;
        this.offerType = offerType;
        this.financial = financial;
        this.bidSendingDate = bidSendingDate;
        this.bidDeadline = bidDeadline;
        this.bidOpeningDate = bidOpeningDate;
        this.evaluatedDate = evaluatedDate;
        this.approvedDate = approvedDate;
        this.contractDate = contractDate;
        this.contractExecDate = contractExecDate;
        this.techEvalStandard = techEvalStandard;
        this.comEvalStandard = comEvalStandard;
        this.evalKind = evalKind;
    }

    public TenderPlanBean(int tenId, int createdEmp, String tenderNumber, String createdDate, String tenderLetterDate, String packName, String subject, String foundation, String supplyScope, String form, int offerType, String financial, String bidSendingDate, String bidDeadline, String bidOpeningDate, String evaluatedDate, String approvedDate, String contractDate, String contractExecDate, String bidSendingTime, String bidDeadlineTime, String bidOpeningTime, String evaluatedTime, String approvedTime, String contractTime, String contractExecTime, String techEvalStandard, String comEvalStandard, int evalKind, String requestNumber, int handleEmp, String handleEmpName, int handledStatus, String status, String deliveryTime, int cerId, double financialPreTax, int IsNeedHighLight, String cerText, String currency, String proName, String orgName, String orgNameUpcase, String offerTypeStr, int stt, String day, String month, String year, String abbreviate) {
        this.tenId = tenId;
        this.createdEmp = createdEmp;
        this.tenderNumber = tenderNumber;
        this.createdDate = createdDate;
        this.tenderLetterDate = tenderLetterDate;
        this.packName = packName;
        this.subject = subject;
        this.foundation = foundation;
        this.supplyScope = supplyScope;
        this.form = form;
        this.offerType = offerType;
        this.financial = financial;
        this.bidSendingDate = bidSendingDate;
        this.bidDeadline = bidDeadline;
        this.bidOpeningDate = bidOpeningDate;
        this.evaluatedDate = evaluatedDate;
        this.approvedDate = approvedDate;
        this.contractDate = contractDate;
        this.contractExecDate = contractExecDate;
        this.bidSendingTime = bidSendingTime;
        this.bidDeadlineTime = bidDeadlineTime;
        this.bidOpeningTime = bidOpeningTime;
        this.evaluatedTime = evaluatedTime;
        this.approvedTime = approvedTime;
        this.contractTime = contractTime;
        this.contractExecTime = contractExecTime;
        this.techEvalStandard = techEvalStandard;
        this.comEvalStandard = comEvalStandard;
        this.evalKind = evalKind;
        this.requestNumber = requestNumber;
        this.handleEmp = handleEmp;
        this.handleEmpName = handleEmpName;
        this.handledStatus = handledStatus;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.cerId = cerId;
        this.financialPreTax = financialPreTax;
        this.IsNeedHighLight = IsNeedHighLight;
        this.cerText = cerText;
        this.currency = currency;
        this.proName = proName;
        this.orgName = orgName;
        this.orgNameUpcase = orgNameUpcase;
        this.offerTypeStr = offerTypeStr;
        this.stt = stt;
        this.day = day;
        this.month = month;
        this.year = year;
        this.abbreviate = abbreviate;
    }

    public TenderPlanBean(int tenId, int createdEmp, String tenderNumber, String createdDate, String tenderLetterDate, String packName, String subject, String foundation, String supplyScope, String form, int offerType, String financial, String bidSendingDate, String bidDeadline, String bidOpeningDate, String evaluatedDate, String approvedDate, String contractDate, String contractExecDate, String bidSendingTime, String bidDeadlineTime, String bidOpeningTime, String evaluatedTime, String approvedTime, String contractTime, String contractExecTime, String techEvalStandard, String comEvalStandard, int evalKind, String requestNumber, int handleEmp, String handleEmpName, int handledStatus, String status, String deliveryTime, int cerId, double financialPreTax, int IsNeedHighLight, String cerText, String currency, String proName, String orgName, String orgNameUpcase, String offerTypeStr, int stt, String day, String month, String year, String abbreviate, String packNameUpcase) {
        this.tenId = tenId;
        this.createdEmp = createdEmp;
        this.tenderNumber = tenderNumber;
        this.createdDate = createdDate;
        this.tenderLetterDate = tenderLetterDate;
        this.packName = packName;
        this.subject = subject;
        this.foundation = foundation;
        this.supplyScope = supplyScope;
        this.form = form;
        this.offerType = offerType;
        this.financial = financial;
        this.bidSendingDate = bidSendingDate;
        this.bidDeadline = bidDeadline;
        this.bidOpeningDate = bidOpeningDate;
        this.evaluatedDate = evaluatedDate;
        this.approvedDate = approvedDate;
        this.contractDate = contractDate;
        this.contractExecDate = contractExecDate;
        this.bidSendingTime = bidSendingTime;
        this.bidDeadlineTime = bidDeadlineTime;
        this.bidOpeningTime = bidOpeningTime;
        this.evaluatedTime = evaluatedTime;
        this.approvedTime = approvedTime;
        this.contractTime = contractTime;
        this.contractExecTime = contractExecTime;
        this.techEvalStandard = techEvalStandard;
        this.comEvalStandard = comEvalStandard;
        this.evalKind = evalKind;
        this.requestNumber = requestNumber;
        this.handleEmp = handleEmp;
        this.handleEmpName = handleEmpName;
        this.handledStatus = handledStatus;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.cerId = cerId;
        this.financialPreTax = financialPreTax;
        this.IsNeedHighLight = IsNeedHighLight;
        this.cerText = cerText;
        this.currency = currency;
        this.proName = proName;
        this.orgName = orgName;
        this.orgNameUpcase = orgNameUpcase;
        this.offerTypeStr = offerTypeStr;
        this.stt = stt;
        this.day = day;
        this.month = month;
        this.year = year;
        this.abbreviate = abbreviate;
        this.packNameUpcase = packNameUpcase;
    }

    public String getPackNameUpcase() {
        return packNameUpcase;
    }

    public void setPackNameUpcase(String packNameUpcase) {
        this.packNameUpcase = packNameUpcase;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public String getOrgNameUpcase() {
        return orgNameUpcase;
    }

    public void setOrgNameUpcase(String orgNameUpcase) {
        this.orgNameUpcase = orgNameUpcase;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getOfferTypeStr() {
        return offerTypeStr;
    }

    public void setOfferTypeStr(String offerTypeStr) {
        this.offerTypeStr = offerTypeStr;
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
        return financial;
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

    public int getEvalKind() {
        return evalKind;
    }

    public void setEvalKind(int evalKind) {
        this.evalKind = evalKind;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getHandleEmp() {
        return handleEmp;
    }

    public void setHandleEmp(int handleEmp) {
        this.handleEmp = handleEmp;
    }

    public String getHandleEmpName() {
        return handleEmpName;
    }

    public void setHandleEmpName(String handleEmpName) {
        this.handleEmpName = handleEmpName;
    }

    public int getHandledStatus() {
        return handledStatus;
    }

    public void setHandledStatus(int handledStatus) {
        this.handledStatus = handledStatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCerId() {
        return cerId;
    }

    public void setCerId(int cerId) {
        this.cerId = cerId;
    }

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

    public int getIsNeedHighLight() {
        return IsNeedHighLight;
    }

    public void setIsNeedHighLight(int IsNeedHighLight) {
        this.IsNeedHighLight = IsNeedHighLight;
    }

    public String getCerText() {
        return cerText;
    }

    public void setCerText(String cerText) {
        this.cerText = cerText;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getTenderLetterDate() {
        return tenderLetterDate;
    }

    public void setTenderLetterDate(String tenderLetterDate) {
        this.tenderLetterDate = tenderLetterDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRos() {
        return ros;
    }

    public void setRos(String ros) {
        this.ros = ros;
    }
}
