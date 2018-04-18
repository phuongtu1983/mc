package com.venus.mc.workReport;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.TenderEvaluateVendorDAO;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
import java.text.SimpleDateFormat;

public class TenderLetterReport
  extends SpineReportParser
{
  public TenderLetterReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    TenderLetterDetailBean bean = (TenderLetterDetailBean)object;
    if (bean == null) {
      bean = new TenderLetterDetailBean();
    }
    TenderPlanDAO tenDAO = new TenderPlanDAO();
    TenderPlanBean tenBean = null;
    try
    {
      tenBean = tenDAO.getTenderPlan(bean.getTenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (tenBean == null) {
      tenBean = new TenderPlanBean();
    }
    int kind = 0;
    TenderLetterDetailDAO detDAO = new TenderLetterDetailDAO();
    
    kind = detDAO.getKindOfVendor(bean.getDetId());
    
    TenderEvaluateVendorDAO tevDAO = new TenderEvaluateVendorDAO();
    TenderEvaluateVendorBean tevBean = null;
    try
    {
      tevBean = tevDAO.getTenderEvaluateVendor(bean.getTevId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (tevBean == null) {
      return;
    }
    VendorDAO vendorDAO = new VendorDAO();
    VendorBean vendorBean = null;
    try
    {
      vendorBean = vendorDAO.getVendor(tevBean.getVenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (vendorBean == null) {
      return;
    }
    if (kind == 1) {
      setText("mcrp_place_delivery", "DAP PTSC MC Warehouse, No 65A, 30/4 Road, Ward 9, Vung tau City, S.R Vietnam");
    } else {
      setText("mcrp_place_delivery", "CIF Ho Chi Minh Port, Ho Chi Minh City, S.R Vietnam");
    }
    setText("mcrp_d1", "ng�y " + DateUtil.today("dd") + " th�ng " + DateUtil.today("MM") + " n?m " + DateUtil.today("yyyy"));
    
    setText("year", "");
    SimpleDateFormat sdf = null;
    setText("mcrp_number", bean.getSubfix());
    setText("mcrp_date", bean.getDate());
    
    setText("mcrp_fax", vendorBean.getFax());
    setText("mcrp_1", vendorBean.getName());
    setText("mcrp_1_1", vendorBean.getName().toUpperCase());
    setText("mcrp_2", "?i?n tho?i : " + vendorBean.getPhone() + "     Fax: " + vendorBean.getFax());
    setText("mcrp_3", "??a ch? : " + vendorBean.getAddress());
    setText("mcrp_4", tenBean.getBidDeadlineTime());
    setText("mcrp_5", tenBean.getBidDeadline());
    setText("mcrp_6", tenBean.getBidDeadline());
    setText("mcrp_0", tenBean.getPackName());
    
    setText("mcrp_pro", "");
    setText("mcrp_packName", tenBean.getPackName());
  }
}
