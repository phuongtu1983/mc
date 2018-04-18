package com.venus.mc.workReport;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.ReportDamageBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ReportDamageDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class ReportDamageReport
  extends SpineReportParser
{
  private ArrayList arrEquipment;
  private String hdEquipmentRow = "hdEquipmentRow";
  
  public ReportDamageReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    String rdIdObject = (String)object;
    String[] o = rdIdObject.split(":");
    int rdId = Integer.parseInt(o[0]);
    String orgName = o[1];
    ReportDamageBean bean = null;
    ReportDamageDAO rdDAO = new ReportDamageDAO();
    try
    {
      bean = rdDAO.getReport(rdId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    setText("mcrp_number", bean.getRdNumber());
    setText("mcrp_dd", DateUtil.formatDate(date, "dd"));
    setText("mcrp_MM", DateUtil.formatDate(date, "MM"));
    setText("mcrp_yyyy", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_orgName", orgName);
    setText("mcrp_time", bean.getCreatedTime());
    setText("mcrp_location", bean.getCreatedLocation());
    setText("mcrp_managerEmpName", bean.getManagerEmpName());
    setText("mcrp_managerEquipmentEmpName", bean.getManagerEquipmentEmpName());
    setText("mcrp_usedEmpName", bean.getUsedEmpName());
    setText("mcrp_equName", bean.getEquName());
    setText("mcrp_usedCode", bean.getUsedCode());
    setText("mcrp_manageCode", bean.getManageCode());
    setText("mcrp_comment", bean.getComment());
    switch (bean.getStatus())
    {
    case 1: 
      setCheck("checkbox1", true);
      break;
    case 2: 
      setCheck("checkbox2", true);
    }
    Hashtable map = new Hashtable();
    
    setArrTable(map);
  }
}
