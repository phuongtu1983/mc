package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class ContractFollowBean {

    private int folId;
    private int conId;
    private int proId;
    private int orgId;
    private String folNumber;
    private String conNumber;
    private String createdDate;
    private String serviceType;
    private String createdTime;
    private String startTime;
    private String endTime;
    private String serviceAbility;
    private String serviceLevel;
    private String serviceEquipment;
    private String serviceProgress;
    private String serviceSafety;
    private String serviceOther;
    private String serviceCooperate;
    private String goodAbility;
    private String goodProgress;
    private String goodCertificate;
    private String goodQuality;
    private String goodOther;
    private String goodCooperate;
    private String comments;
    private int createdEmp;

    public ContractFollowBean(int folId, int conId, int proId, int orgId, String folNumber, String conNumber, String createdDate, String serviceType, String createdTime, String startTime, String endTime, String serviceAbility, String serviceLevel, String serviceEquipment, String serviceProgress, String serviceSafety, String serviceOther, String serviceCooperate, String goodAbility, String goodProgress, String goodCertificate, String goodQuality, String goodOther, String goodCooperate, String comments, int createdEmp) {
        this.folId = folId;
        this.conId = conId;
        this.proId = proId;
        this.orgId = orgId;
        this.folNumber = folNumber;
        this.conNumber = conNumber;
        this.createdDate = createdDate;
        this.serviceType = serviceType;
        this.createdTime = createdTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceAbility = serviceAbility;
        this.serviceLevel = serviceLevel;
        this.serviceEquipment = serviceEquipment;
        this.serviceProgress = serviceProgress;
        this.serviceSafety = serviceSafety;
        this.serviceOther = serviceOther;
        this.serviceCooperate = serviceCooperate;
        this.goodAbility = goodAbility;
        this.goodProgress = goodProgress;
        this.goodCertificate = goodCertificate;
        this.goodQuality = goodQuality;
        this.goodOther = goodOther;
        this.goodCooperate = goodCooperate;
        this.comments = comments;
        this.createdEmp = createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getComments() {
        return comments;
    }

    public int getConId() {
        return conId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getFolId() {
        return folId;
    }

    public String getGoodAbility() {
        return goodAbility;
    }

    public String getGoodCertificate() {
        return goodCertificate;
    }

    public String getGoodCooperate() {
        return goodCooperate;
    }

    public String getGoodOther() {
        return goodOther;
    }

    public String getGoodProgress() {
        return goodProgress;
    }

    public String getGoodQuality() {
        return goodQuality;
    }

    public int getOrgId() {
        return orgId;
    }

    public int getProId() {
        return proId;
    }

    public String getServiceAbility() {
        return serviceAbility;
    }

    public String getServiceCooperate() {
        return serviceCooperate;
    }

    public String getServiceEquipment() {
        return serviceEquipment;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public String getServiceOther() {
        return serviceOther;
    }

    public String getServiceProgress() {
        return serviceProgress;
    }

    public String getServiceSafety() {
        return serviceSafety;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFolNumber() {
        return folNumber;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setFolNumber(String folNumber) {
        this.folNumber = folNumber;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFolId(int folId) {
        this.folId = folId;
    }

    public void setGoodAbility(String goodAbility) {
        this.goodAbility = goodAbility;
    }

    public void setGoodCertificate(String goodCertificate) {
        this.goodCertificate = goodCertificate;
    }

    public void setGoodCooperate(String goodCooperate) {
        this.goodCooperate = goodCooperate;
    }

    public void setGoodOther(String goodOther) {
        this.goodOther = goodOther;
    }

    public void setGoodProgress(String goodProgress) {
        this.goodProgress = goodProgress;
    }

    public void setGoodQuality(String goodQuality) {
        this.goodQuality = goodQuality;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public void setServiceAbility(String serviceAbility) {
        this.serviceAbility = serviceAbility;
    }

    public void setServiceCooperate(String serviceCooperate) {
        this.serviceCooperate = serviceCooperate;
    }

    public void setServiceEquipment(String serviceEquipment) {
        this.serviceEquipment = serviceEquipment;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public void setServiceOther(String serviceOther) {
        this.serviceOther = serviceOther;
    }

    public void setServiceProgress(String serviceProgress) {
        this.serviceProgress = serviceProgress;
    }

    public void setServiceSafety(String serviceSafety) {
        this.serviceSafety = serviceSafety;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ContractFollowBean() {
    }
}
