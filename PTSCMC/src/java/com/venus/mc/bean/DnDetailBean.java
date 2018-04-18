//mai vinh loc
package com.venus.mc.bean;

public class DnDetailBean {

    //fields region
    private int detId; // primary key
    private int dnId;
    private int matId; // foreign key : reference to material(mat_id)
    private int conDetId;
    private double quantity;
    private double qtDn;
    private double qtTemp;
    private String unit;
    private String matName;
    private String matCode;
    private double price;
    private String note;
    private int reqDetailId;
    private String requestNumber;
    private String materialGrade;
    private String materialType;
    private String size1;
    private String size2;
    private String size3;
    private String poNo;
    private String mrirNo;
    private String heatNo;
    private String traceNo;
    private String area;
    private String weight;
    private String location;
    private String remark;
    private String moveCreateMrir;
    private String receiveMrir;
    private String moveCreateMsv;
    private String receiveMsv;
    private String mrirNumber;
    private String msvNumber;
    private String mrirCreatedDate;
    private String msvCreatedDate;
    private int orgHandle;
    private int stt;

    //constructure region
    public DnDetailBean() {
    }

    public DnDetailBean(int detId) {
        this.detId = detId;
    }

    public DnDetailBean(int detId, int dnId, int matId, int conDetId, double quantity, double qtDn, double qtTemp, String unit, String matName, String matCode, double price, String note, int reqDetailId, String requestNumber, String materialGrade, String materialType, String size1, String size2, String size3, String poNo, String mrirNo, String heatNo, String traceNo, String area, String weight, String location, String remark, String moveCreateMrir, String receiveMrir, String moveCreateMsv, String receiveMsv, String mrirNumber, String msvNumber, String mrirCreatedDate, String msvCreatedDate) {
        this.detId = detId;
        this.dnId = dnId;
        this.matId = matId;
        this.conDetId = conDetId;
        this.quantity = quantity;
        this.qtDn = qtDn;
        this.qtTemp = qtTemp;
        this.unit = unit;
        this.matName = matName;
        this.matCode = matCode;
        this.price = price;
        this.note = note;
        this.reqDetailId = reqDetailId;
        this.requestNumber = requestNumber;
        this.materialGrade = materialGrade;
        this.materialType = materialType;
        this.size1 = size1;
        this.size2 = size2;
        this.size3 = size3;
        this.poNo = poNo;
        this.mrirNo = mrirNo;
        this.heatNo = heatNo;
        this.traceNo = traceNo;
        this.area = area;
        this.weight = weight;
        this.location = location;
        this.remark = remark;
        this.moveCreateMrir = moveCreateMrir;
        this.receiveMrir = receiveMrir;
        this.moveCreateMsv = moveCreateMsv;
        this.receiveMsv = receiveMsv;
        this.mrirNumber = mrirNumber;
        this.msvNumber = msvNumber;
        this.mrirCreatedDate = mrirCreatedDate;
        this.msvCreatedDate = msvCreatedDate;
    }

    public DnDetailBean(String matName, String matCode, String note, String unit,double quantity,int stt) {
        
        this.quantity = quantity;
        this.unit = unit;
        this.matName = matName;
        this.matCode = matCode;
        this.note = note;
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public int getConDetId() {
        return conDetId;
    }

    public void setConDetId(int conDetId) {
        this.conDetId = conDetId;
    }

    

    public String getMsvCreatedDate() {
        return msvCreatedDate;
    }

    public void setMsvCreatedDate(String msvCreatedDate) {
        this.msvCreatedDate = msvCreatedDate;
    }

    public String getMrirCreatedDate() {
        return mrirCreatedDate;
    }

    public void setMrirCreatedDate(String mrirCreatedDate) {
        this.mrirCreatedDate = mrirCreatedDate;
    }

    public double getQtDn() {
        return qtDn;
    }

    public void setQtDn(double qtDn) {
        this.qtDn = qtDn;
    }

    public double getQtTemp() {
        return qtTemp;
    }

    public void setQtTemp(double qtTemp) {
        this.qtTemp = qtTemp;
    }

    public String getMrirNumber() {
        return mrirNumber;
    }

    public String getMsvNumber() {
        return msvNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
    }

    public String getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public String getMoveCreateMsv() {
        return moveCreateMsv;
    }

    public String getReceiveMrir() {
        return receiveMrir;
    }

    public String getReceiveMsv() {
        return receiveMsv;
    }

    public void setMoveCreateMrir(String moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public void setMoveCreateMsv(String moveCreateMsv) {
        this.moveCreateMsv = moveCreateMsv;
    }

    public void setReceiveMrir(String receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public void setReceiveMsv(String receiveMsv) {
        this.receiveMsv = receiveMsv;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getDnId() {
        return dnId;
    }

    public String getNote() {
        return note;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getArea() {
        return area;
    }

    public String getHeatNo() {
        return heatNo;
    }

    public String getLocation() {
        return location;
    }

    public String getMaterialGrade() {
        return materialGrade;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getMrirNo() {
        return mrirNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public String getRemark() {
        return remark;
    }

    public String getSize1() {
        return size1;
    }

    public String getSize2() {
        return size2;
    }

    public String getSize3() {
        return size3;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setHeatNo(String heatNo) {
        this.heatNo = heatNo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMaterialGrade(String materialGrade) {
        this.materialGrade = materialGrade;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setMrirNo(String mrirNo) {
        this.mrirNo = mrirNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public void setSize3(String size3) {
        this.size3 = size3;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public String getWeight() {
        return weight;
    }
}
