package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanCertificateBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.util.MCUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class TenderPlanAppendixReport
  extends SpineReportParser
{
  private ArrayList arrMaterial;
  private String materialRow = "materialRow";
  
  public TenderPlanAppendixReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    TenderPlanBean bean = (TenderPlanBean)object;
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    setText("mcrp_packName", bean.getPackName().toUpperCase());
    setText("mcrp_deadlineTime", bean.getBidDeadlineTime());
    setText("mcrp_deadline", bean.getBidDeadline());
    setText("mcrp_tenderNumber", bean.getTenderNumber());
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    setText("mcrp_year", sdf.format(date));
    setText("mcrp_deliveryTime", bean.getDeliveryTime());
    setText("mcrp_certificate", getTenderPlanCertificate(bean.getTenId()));
    setText("mcrp_pro", bean.getProName());
    try
    {
      ArrayList arrReq = tenderDAO.getRequestsOfTenderPlan(bean.getTenId());
      RequestBean reqBean = null;
      int proId = 0;
      int proCount = 0;
      String proName = "";
      for (int i = 0; i < arrReq.size(); i++)
      {
        reqBean = (RequestBean)arrReq.get(i);
        if ((reqBean.getWhichUse() == RequestFormBean.WHICHUSE_PROJECT) && 
          (proId != reqBean.getProId()))
        {
          proId = reqBean.getProId();
          proCount++;
          proName = reqBean.getWhichUseName();
        }
      }
      String servered = "";
      if (proCount == 0) {
        servered = MCUtil.getBundleString("message.tenderplan.servered.company");
      } else if (proCount == 1) {
        servered = MCUtil.getBundleString("message.tenderplan.servered.project") + " " + proName;
      } else {
        servered = MCUtil.getBundleString("message.tenderplan.servered.multiproject");
      }
      setText("mcrp_servered", servered);
    }
    catch (Exception localException) {}
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.materialRow, this);
    try
    {
      this.arrMaterial = tenderDAO.getTenderPlanDetailsForExport(bean.getTenId());
    }
    catch (Exception localException1) {}
    if (this.arrMaterial == null) {
      this.arrMaterial = new ArrayList();
    }
    row.setRowCount(this.arrMaterial.size());
    map.put(this.materialRow, row);
    
    setArrTable(map);
  }
  
  private String getMaterialText(int i, String tab)
  {
    String text = "";
    TenderPlanDetailBean bean = null;
    if (i < this.arrMaterial.size())
    {
      bean = (TenderPlanDetailBean)this.arrMaterial.get(i);
      if (tab.equals("mcrp_n1")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_matName")) {
        text = bean.getMatName();
      } else if (tab.equals("mcrp_origin")) {
        text = bean.getMatOrigin();
      } else if (tab.equals("mcrp_unit")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_quantity")) {
        text = NumberUtil.formatNumberDefault(bean.getQuantity()) + "";
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.materialRow)) {
      return getMaterialText(i, tab);
    }
    return "";
  }
  
  private String getTenderPlanCertificate(int tenId)
  {
    String cer = "";
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    try
    {
      ArrayList arrCer = tenderDAO.getTenderPlanCertificate(tenId);
      TenderPlanCertificateBean certificate = null;
      for (int i = 0; i < arrCer.size(); i++)
      {
        certificate = (TenderPlanCertificateBean)arrCer.get(i);
        cer = cer + "," + certificate.getCerName();
      }
      if (!cer.equals("")) {
        cer = cer.substring(1);
      }
    }
    catch (Exception localException) {}
    return cer;
  }
}
