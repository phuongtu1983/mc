//mai vinh loc
package com.venus.mc.process.purchasing.deliverynotice;

public class DnFormBean extends org.apache.struts.action.ActionForm {

    final public static int DN_NORMAL = 1;
    final public static int DN_STORE2_PRO = 2;
    final public static int DN_STORE2_COM = 3;
    final public static int DN_STORE1_PRO = 4;
    final public static int STATUS1 = 1; //Da xu ly
    final public static int STATUS2 = 2; //Chua xu ly
    //fields region
    private int dnId; // primary key
    private String createdDate;
    private int createdEmp;
    private String dnNumber;
    private String contractNumber;
    private int conId;
    private int drId;
    private String expectedDate;
    private String extensionDate;
    private String deliveryPlace;
    private String deliveryPresenter;
    private int whichUse;
    private int[] delDniId;
    private int[] dniId; // primary key
    private String[] moveCreateMrir;
    private String[] receiveMrir;
    private String[] moveCreateMsv;
    private String[] receiveMsv;
    private int[] delDetId;
    private int[] detId; // primary key
    private int[] matId; // foreign key : reference to material(mat_id)
    private String[] quantity;
    private String[] conDetId;
    private String[] qtTemp;
    private String[] qtDn;
    private String[] unit;
    private String[] matName;
    private String[] matCode;
    private double[] price;
    private String[] note;
    private int[] reqDetailId;
    private String[] materialGrade;
    private String[] materialType;
    private String[] size1;
    private String[] size2;
    private String[] size3;
    private String[] poNo;
    private String[] mrirNo;
    private String[] heatNo;
    private String[] traceNo;
    private String[] area;
    private String[] weight;
    private String[] location;
    private String[] remark;
    private int createdOrg;
    private int proId;
    private int kind;
    private int status;
    private String empName;
    private String statusName;
    private String statusInvoice;
    private String invoice;
    private String paymentDate;
    private String invoiceDate;
    private double amount;
    private String contractPaymentDate;
    private String restDay;
    private String responseEmp;
    private double sumVAT;
    private double sumNotVAT;
    private int highlight;
    private String proName;
    private String createdDateMrir;
    private String receiveDateMrir;
    private String createdDateMsv;
    private String receiveDateMsv;
    private String followEmp;
    private int conKind;
    private String actualDate;
    private int parentId;
    private int orgHandle;

    //constructure region
    public DnFormBean() {
    }

    public void setConDetId(String[] conDetId) {
        this.conDetId = conDetId;
    }

    public String[] getConDetId() {
        return conDetId;
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setStatusInvoice(String statusInvoice) {
        this.statusInvoice = statusInvoice;
    }

    public String getStatusInvoice() {
        return statusInvoice;
    }

    public void setConKind(int conKind) {
        this.conKind = conKind;
    }

    public int getConKind() {
        return conKind;
    }

    public void setFollowEmp(String followEmp) {
        this.followEmp = followEmp;
    }

    public String getFollowEmp() {
        return followEmp;
    }

    public void setReceiveDateMsv(String receiveDateMsv) {
        this.receiveDateMsv = receiveDateMsv;
    }

    public void setReceiveDateMrir(String receiveDateMrir) {
        this.receiveDateMrir = receiveDateMrir;
    }

    public void setCreatedDateMsv(String createdDateMsv) {
        this.createdDateMsv = createdDateMsv;
    }

    public void setCreatedDateMrir(String createdDateMrir) {
        this.createdDateMrir = createdDateMrir;
    }

    public String getReceiveDateMsv() {
        return receiveDateMsv;
    }

    public String getReceiveDateMrir() {
        return receiveDateMrir;
    }

    public String getCreatedDateMsv() {
        return createdDateMsv;
    }

    public String getCreatedDateMrir() {
        return createdDateMrir;
    }

    public void setQtDn(String[] qtDn) {
        this.qtDn = qtDn;
    }

    public String[] getQtDn() {
        return qtDn;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setQtTemp(String[] qtTemp) {
        this.qtTemp = qtTemp;
    }

    public String[] getQtTemp() {
        return qtTemp;
    }

    public void setExtensionDate(String extensionDate) {
        this.extensionDate = extensionDate;
    }

    public String getExtensionDate() {
        return extensionDate;
    }

    public int[] getDelDniId() {
        return delDniId;
    }

    public int[] getDniId() {
        return dniId;
    }

    public void setDelDniId(int[] delDniId) {
        this.delDniId = delDniId;
    }

    public void setDniId(int[] dniId) {
        this.dniId = dniId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public void setDelDetId(int[] delDetId) {
        this.delDetId = delDetId;
    }

    public int[] getDelDetId() {
        return delDetId;
    }

    public int getConId() {
        return conId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public String getDeliveryPresenter() {
        return deliveryPresenter;
    }

    public int[] getDetId() {
        return detId;
    }

    public int getDnId() {
        return dnId;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public int getDrId() {
        return drId;
    }

    public String[] getMatCode() {
        return matCode;
    }

    public int[] getMatId() {
        return matId;
    }

    public String[] getMatName() {
        return matName;
    }

    public String[] getNote() {
        return note;
    }

    public double[] getPrice() {
        return price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setDeliveryPresenter(String deliveryPresenter) {
        this.deliveryPresenter = deliveryPresenter;
    }

    public void setDetId(int[] detId) {
        this.detId = detId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public void setMatCode(String[] matCode) {
        this.matCode = matCode;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public void setMatName(String[] matName) {
        this.matName = matName;
    }

    public void setNote(String[] note) {
        this.note = note;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public int getWhichUse() {
        return whichUse;
    }

    public void setWhichUse(int whichUse) {
        this.whichUse = whichUse;
    }

    public String[] getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public String[] getMoveCreateMsv() {
        return moveCreateMsv;
    }

    public String[] getReceiveMrir() {
        return receiveMrir;
    }

    public String[] getReceiveMsv() {
        return receiveMsv;
    }

    public void setMoveCreateMrir(String[] moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public void setMoveCreateMsv(String[] moveCreateMsv) {
        this.moveCreateMsv = moveCreateMsv;
    }

    public void setReceiveMrir(String[] receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public void setReceiveMsv(String[] receiveMsv) {
        this.receiveMsv = receiveMsv;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public String[] getArea() {
        return area;
    }

    public String[] getHeatNo() {
        return heatNo;
    }

    public String[] getLocation() {
        return location;
    }

    public String[] getMaterialGrade() {
        return materialGrade;
    }

    public String[] getMaterialType() {
        return materialType;
    }

    public String[] getMrirNo() {
        return mrirNo;
    }

    public String[] getPoNo() {
        return poNo;
    }

    public String[] getRemark() {
        return remark;
    }

    public String[] getSize1() {
        return size1;
    }

    public String[] getSize2() {
        return size2;
    }

    public String[] getSize3() {
        return size3;
    }

    public String[] getTraceNo() {
        return traceNo;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public void setHeatNo(String[] heatNo) {
        this.heatNo = heatNo;
    }

    public void setMaterialGrade(String[] materialGrade) {
        this.materialGrade = materialGrade;
    }

    public void setMaterialType(String[] materialType) {
        this.materialType = materialType;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public void setMrirNo(String[] mrirNo) {
        this.mrirNo = mrirNo;
    }

    public void setPoNo(String[] poNo) {
        this.poNo = poNo;
    }

    public void setRemark(String[] remark) {
        this.remark = remark;
    }

    public void setSize1(String[] size1) {
        this.size1 = size1;
    }

    public void setSize2(String[] size2) {
        this.size2 = size2;
    }

    public void setSize3(String[] size3) {
        this.size3 = size3;
    }

    public void setTraceNo(String[] traceNo) {
        this.traceNo = traceNo;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public double getAmount() {
        return amount;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getRestDay() {
        return restDay;
    }

    public String getResponseEmp() {
        return responseEmp;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setContractPaymentDate(String contractPaymentDate) {
        this.contractPaymentDate = contractPaymentDate;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setResponseEmp(String responseEmp) {
        this.responseEmp = responseEmp;
    }

    public void setRestDay(String restDay) {
        this.restDay = restDay;
    }

    public String getContractPaymentDate() {
        return contractPaymentDate;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

    public double getSumNotVAT() {
        return sumNotVAT;
    }

    public void setSumNotVAT(double sumNotVAT) {
        this.sumNotVAT = sumNotVAT;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
