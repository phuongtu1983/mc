/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.request;

import com.venus.mc.bean.RequestDetailBean;

/**
 *
 * @author phuongtu
 */
public class RequestDetailFormBean extends org.apache.struts.action.ActionForm {

    private int detId;
    private int reqId;
    private int matId;
    private int conId;
    private String unit;
    private double presentQuantity;
    private double additionalQuantity;
    private double requestQuantity;
    private double remainQuantity;
    private String provideDate;
    private int checkAppro;
    private int stockAppro;
    private int plandepAppro;
    private int bomAppro;
    private String intermemoNote;
    private String step;
    private int stepId;
    private int materialKind;
    private String matName;
    private String matCode;
    private String ReqNumber;
    private String reqDate;
    private String reqOrg;
    private String reqProject;
    private int no;
    private int empId;
    private int isCancel;
    private int isAssigned;
    private int isOtherDepartment;
    private String employee;
    private int canChangeMaterial;

    //constructure region
    public RequestDetailFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RequestDetailFormBean(RequestDetailBean bean) {
        this.detId = bean.getDetId();
        this.reqId = bean.getReqId();
        this.matId = bean.getMatId();
        this.conId = bean.getConId();
        this.unit = bean.getUnit();
        this.presentQuantity = bean.getPresentQuantity();
        this.additionalQuantity = bean.getAdditionalQuantity();
        this.requestQuantity = bean.getRequestQuantity();
        this.provideDate = bean.getProvideDate();
        this.checkAppro = bean.getCheckAppro();
        this.stockAppro = bean.getStockAppro();
        this.plandepAppro = bean.getPlandepAppro();
        this.bomAppro = bean.getBomAppro();
        this.intermemoNote = bean.getIntermemoNote();
        this.step = bean.getStep();
        this.stepId = bean.getStepId();
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPresentQuantity() {
        return this.presentQuantity;
    }

    public void setPresentQuantity(double presentQuantity) {
        this.presentQuantity = presentQuantity;
    }

    public double getAdditionalQuantity() {
        return this.additionalQuantity;
    }

    public void setAdditionalQuantity(double additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public double getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(double requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String getProvideDate() {
        return this.provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public int getCheckAppro() {
        return this.checkAppro;
    }

    public void setCheckAppro(int checkAppro) {
        this.checkAppro = checkAppro;
    }

    public int getPlandepAppro() {
        return this.plandepAppro;
    }

    public void setPlandepAppro(int plandepAppro) {
        this.plandepAppro = plandepAppro;
    }

    public String getIntermemoNote() {
        return this.intermemoNote;
    }

    public void setIntermemoNote(String intermemoNote) {
        this.intermemoNote = intermemoNote;
    }

    public String getStep() {
        return this.step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getStepId() {
        return this.stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public int getStockAppro() {
        return stockAppro;
    }

    public void setStockAppro(int stockAppro) {
        this.stockAppro = stockAppro;
    }

    public int getBomAppro() {
        return bomAppro;
    }

    public void setBomAppro(int bomAppro) {
        this.bomAppro = bomAppro;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getReqNumber() {
        return ReqNumber;
    }

    public void setReqNumber(String ReqNumber) {
        this.ReqNumber = ReqNumber;
    }

    public int getMaterialKind() {
        return materialKind;
    }

    public void setMaterialKind(int materialKind) {
        this.materialKind = materialKind;
    }

    public double getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(double remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqOrg() {
        return reqOrg;
    }

    public void setReqOrg(String reqOrg) {
        this.reqOrg = reqOrg;
    }

    public String getReqProject() {
        return reqProject;
    }

    public void setReqProject(String reqProject) {
        this.reqProject = reqProject;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public int getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(int isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getIsOtherDepartment() {
        return isOtherDepartment;
    }

    public void setIsOtherDepartment(int isOtherDepartment) {
        this.isOtherDepartment = isOtherDepartment;
    }

    public int getCanChangeMaterial() {
        return canChangeMaterial;
    }

    public void setCanChangeMaterial(int canChangeMaterial) {
        this.canChangeMaterial = canChangeMaterial;
    }
}
