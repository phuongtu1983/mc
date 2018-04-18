
/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.tenderplan;

public class TenderEvaluateVendorFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int tevId;
    private int tenId;
    private int venId;
    private int sent;
    private int bidded;
    private String biddedPage;
    private String biddedFoundation;
    private int biddedStatus;
    private String quoNo;
    private String quoDate;
    private String bidValidity;
    private int incoterm;
    private String venName;
    private String venPhone;
    private String venFax;
    private String venEmail;
    private String venAddress;
    private String note;
    private int stt;
    private String attn;
    //constructure region
   public TenderEvaluateVendorFormBean() {
       super();
   }

    public TenderEvaluateVendorFormBean(int tevId, int tenId, int venId, int sent, int bidded, String biddedPage, String biddedFoundation, int biddedStatus, String quoNo, String quoDate, String bidValidity, int incoterm, String venName, String venPhone, String venFax, String venEmail, String venAddress, String note, int stt, String attn) {
        this.tevId = tevId;
        this.tenId = tenId;
        this.venId = venId;
        this.sent = sent;
        this.bidded = bidded;
        this.biddedPage = biddedPage;
        this.biddedFoundation = biddedFoundation;
        this.biddedStatus = biddedStatus;
        this.quoNo = quoNo;
        this.quoDate = quoDate;
        this.bidValidity = bidValidity;
        this.incoterm = incoterm;
        this.venName = venName;
        this.venPhone = venPhone;
        this.venFax = venFax;
        this.venEmail = venEmail;
        this.venAddress = venAddress;
        this.note = note;
        this.stt = stt;
        this.attn = attn;
    }

   

    public String getAttn() {
        return attn;
    }

    public void setAttn(String attn) {
        this.attn = attn;
    }

    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getTevId() {
        return tevId;
    }

    public void setTevId(int tevId) {
        this.tevId = tevId;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getVenPhone() {
        return venPhone;
    }

    public void setVenPhone(String venPhone) {
        this.venPhone = venPhone;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getVenFax() {
        return venFax;
    }

    public void setVenFax(String venFax) {
        this.venFax = venFax;
    }

    public String getVenEmail() {
        return venEmail;
    }

    public void setVenEmail(String venEmail) {
        this.venEmail = venEmail;
    }

    public String getVenAddress() {
        return venAddress;
    }

    public void setVenAddress(String venAddress) {
        this.venAddress = venAddress;
    }

    public int getSent() {
        return this.sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public int getBidded() {
        return this.bidded;
    }

    public void setBidded(int bidded) {
        this.bidded = bidded;
    }

    public String getBiddedPage() {
        return this.biddedPage;
    }

    public void setBiddedPage(String biddedPage) {
        this.biddedPage = biddedPage;
    }

    public String getBiddedFoundation() {
        return this.biddedFoundation;
    }

    public void setBiddedFoundation(String biddedFoundation) {
        this.biddedFoundation = biddedFoundation;
    }

    public int getBiddedStatus() {
        return this.biddedStatus;
    }

    public void setBiddedStatus(int biddedStatus) {
        this.biddedStatus = biddedStatus;
    }

    public String getQuoNo() {
        return this.quoNo;
    }

    public void setQuoNo(String quoNo) {
        this.quoNo = quoNo;
    }

    public String getQuoDate() {
        return this.quoDate;
    }

    public void setQuoDate(String quoDate) {
        this.quoDate = quoDate;
    }

    public String getBidValidity() {
        return this.bidValidity;
    }

    public void setBidValidity(String bidValidity) {
        this.bidValidity = bidValidity;
    }

    public int getIncoterm() {
        return this.incoterm;
    }

    public void setIncoterm(int incoterm) {
        this.incoterm = incoterm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
