
/// <summary>
/// Author : phuongtu
/// Created Date : 04/11/2009
/// </summary>
package com.venus.mc.config;

public class ConfigFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int id;
    private String dnTO;
    private String dnCC;
    private String invoiceTO;
    private String invoiceCC;
    private String mrirTO;
    private String mrirCC;
    private String msvTO;
    private String msvCC;
    private String repairTO;
    private String repairCC;
    private String auditTO;
    private String auditCC;
    private String matInTO;
    private String matInCC;
    private String matOutTO;
    private String matOutCC;

    //constructure region
    public ConfigFormBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuditCC() {
        return auditCC;
    }

    public void setAuditCC(String auditCC) {
        this.auditCC = auditCC;
    }

    public String getAuditTO() {
        return auditTO;
    }

    public void setAuditTO(String auditTO) {
        this.auditTO = auditTO;
    }

    public String getDnCC() {
        return dnCC;
    }

    public void setDnCC(String dnCC) {
        this.dnCC = dnCC;
    }

    public String getDnTO() {
        return dnTO;
    }

    public void setDnTO(String dnTO) {
        this.dnTO = dnTO;
    }

    public String getInvoiceCC() {
        return invoiceCC;
    }

    public void setInvoiceCC(String invoiceCC) {
        this.invoiceCC = invoiceCC;
    }

    public String getInvoiceTO() {
        return invoiceTO;
    }

    public void setInvoiceTO(String invoiceTO) {
        this.invoiceTO = invoiceTO;
    }

    public String getMatInCC() {
        return matInCC;
    }

    public void setMatInCC(String matInCC) {
        this.matInCC = matInCC;
    }

    public String getMatInTO() {
        return matInTO;
    }

    public void setMatInTO(String matInTO) {
        this.matInTO = matInTO;
    }

    public String getMatOutCC() {
        return matOutCC;
    }

    public void setMatOutCC(String matOutCC) {
        this.matOutCC = matOutCC;
    }

    public String getMatOutTO() {
        return matOutTO;
    }

    public void setMatOutTO(String matOutTO) {
        this.matOutTO = matOutTO;
    }

    public String getMrirCC() {
        return mrirCC;
    }

    public void setMrirCC(String mrirCC) {
        this.mrirCC = mrirCC;
    }

    public String getMrirTO() {
        return mrirTO;
    }

    public void setMrirTO(String mrirTO) {
        this.mrirTO = mrirTO;
    }

    public String getMsvCC() {
        return msvCC;
    }

    public void setMsvCC(String msvCC) {
        this.msvCC = msvCC;
    }

    public String getMsvTO() {
        return msvTO;
    }

    public void setMsvTO(String msvTO) {
        this.msvTO = msvTO;
    }

    public String getRepairCC() {
        return repairCC;
    }

    public void setRepairCC(String repairCC) {
        this.repairCC = repairCC;
    }

    public String getRepairTO() {
        return repairTO;
    }

    public void setRepairTO(String repairTO) {
        this.repairTO = repairTO;
    }
}
