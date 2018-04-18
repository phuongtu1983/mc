package com.venus.mc.workReport;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractFollowBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.ContractFollowDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContractFollowReport
  extends SpineReportParser
{
  public ContractFollowReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer folIdObject = (Integer)object;
    int folId = folIdObject.intValue();
    ContractFollowBean bean = null;
    ContractFollowDAO contractFollowDAO = new ContractFollowDAO();
    try
    {
      bean = contractFollowDAO.getContractFollow(folId + "");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    ContractDAO conDAO = new ContractDAO();
    ContractBean conBean = null;
    try
    {
      conBean = conDAO.getContract(bean.getConId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (conBean == null) {
      return;
    }
    OrganizationDAO orgDAO = new OrganizationDAO();
    OrganizationBean orgBean = null;
    try
    {
      orgBean = orgDAO.getOrganization(bean.getOrgId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (orgBean == null) {
      return;
    }
    VendorDAO vendorDAO = new VendorDAO();
    VendorBean vendorBean = null;
    try
    {
      vendorBean = vendorDAO.getVendor(conBean.getVenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (vendorBean == null) {
      return;
    }
    ProjectDAO projectDAO = new ProjectDAO();
    ProjectBean projectBean = null;
    try
    {
      projectBean = projectDAO.getProject(bean.getProId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (projectBean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    SimpleDateFormat sdf = null;
    sdf = new SimpleDateFormat("dd");
    setText("mcrp_day", sdf.format(date));
    sdf = new SimpleDateFormat("MM");
    setText("mcrp_month", sdf.format(date));
    sdf = new SimpleDateFormat("yyyy");
    setText("mcrp_year", sdf.format(date));
    
    setText("mcrp_followNumber", bean.getFolNumber());
    setText("mcrp_contractNumber", conBean.getContractNumber());
    setText("mcrp_orgName", orgBean.getName());
    setText("mcrp_vendorName", vendorBean.getName());
    setText("mcrp_vendorAddress", vendorBean.getAddress());
    setText("mcrp_effectedDate", conBean.getEffectedDate());
    if (bean.getServiceType().equals("1")) {
      setText("mcrp_serviceType", MCUtil.getBundleString("message.contractFollow.serviceType1"));
    } else if (bean.getServiceType().equals("2")) {
      setText("mcrp_serviceType", MCUtil.getBundleString("message.contractFollow.serviceType2"));
    }
    setText("mcrp_proName", projectBean.getName());
    setText("mcrp_startTime", bean.getStartTime());
    setText("mcrp_endTime", bean.getEndTime());
    setText("mcrp_serviceOther", bean.getServiceOther());
    setText("mcrp_goodOther", bean.getGoodOther());
    setText("mcrp_comments", bean.getComments());
    if (bean.getServiceType().equals("2"))
    {
      switch (NumberUtil.parseInt(bean.getGoodAbility(), 1))
      {
      case 1: 
        setCheck("checkbox1", true);
        break;
      case 2: 
        setCheck("checkbox2", true);
        break;
      case 3: 
        setCheck("checkbox3", true);
        break;
      case 4: 
        setCheck("checkbox4", true);
      }
      switch (NumberUtil.parseInt(bean.getGoodProgress(), 1))
      {
      case 1: 
        setCheck("checkbox5", true);
        break;
      case 2: 
        setCheck("checkbox6", true);
        break;
      case 3: 
        setCheck("checkbox7", true);
        break;
      case 4: 
        setCheck("checkbox8", true);
      }
      switch (NumberUtil.parseInt(bean.getGoodCertificate(), 1))
      {
      case 1: 
        setCheck("checkbox9", true);
        break;
      case 2: 
        setCheck("checkbox10", true);
        break;
      case 3: 
        setCheck("checkbox11", true);
        break;
      case 4: 
        setCheck("checkbox12", true);
      }
      switch (NumberUtil.parseInt(bean.getGoodQuality(), 1))
      {
      case 1: 
        setCheck("checkbox13", true);
        break;
      case 2: 
        setCheck("checkbox14", true);
        break;
      case 3: 
        setCheck("checkbox15", true);
        break;
      case 4: 
        setCheck("checkbox16", true);
      }
      switch (NumberUtil.parseInt(bean.getGoodCooperate(), 1))
      {
      case 1: 
        setCheck("checkbox17", true);
        break;
      case 2: 
        setCheck("checkbox18", true);
        break;
      case 3: 
        setCheck("checkbox19", true);
      }
      switch (NumberUtil.parseInt(bean.getServiceAbility(), 1))
      {
      case 1: 
        setCheck("checkbox20", true);
        break;
      case 2: 
        setCheck("checkbox21", true);
        break;
      case 3: 
        setCheck("checkbox22", true);
        break;
      case 4: 
        setCheck("checkbox23", true);
      }
    }
    else if (bean.getServiceType().equals("1"))
    {
      switch (NumberUtil.parseInt(bean.getServiceLevel(), 1))
      {
      case 1: 
        setCheck("checkbox24", true);
        break;
      case 2: 
        setCheck("checkbox25", true);
        break;
      case 3: 
        setCheck("checkbox26", true);
        break;
      case 4: 
        setCheck("checkbox27", true);
      }
      switch (NumberUtil.parseInt(bean.getServiceEquipment(), 1))
      {
      case 1: 
        setCheck("checkbox28", true);
        break;
      case 2: 
        setCheck("checkbox29", true);
        break;
      case 3: 
        setCheck("checkbox30", true);
        break;
      case 4: 
        setCheck("checkbox31", true);
      }
      switch (NumberUtil.parseInt(bean.getServiceProgress(), 1))
      {
      case 1: 
        setCheck("checkbox32", true);
        break;
      case 2: 
        setCheck("checkbox33", true);
        break;
      case 3: 
        setCheck("checkbox34", true);
        break;
      case 4: 
        setCheck("checkbox35", true);
      }
      switch (NumberUtil.parseInt(bean.getServiceSafety(), 1))
      {
      case 1: 
        setCheck("checkbox36", true);
        break;
      case 2: 
        setCheck("checkbox37", true);
        break;
      case 3: 
        setCheck("checkbox38", true);
        break;
      case 4: 
        setCheck("checkbox39", true);
      }
      switch (NumberUtil.parseInt(bean.getServiceCooperate(), 1))
      {
      case 1: 
        setCheck("checkbox40", true);
        break;
      case 2: 
        setCheck("checkbox41", true);
        break;
      case 3: 
        setCheck("checkbox42", true);
        break;
      case 4: 
        setCheck("checkbox43", true);
      }
      switch (1)
      {
      case 1: 
        setCheck("checkbox44", true);
        break;
      case 2: 
        setCheck("checkbox45", true);
        break;
      case 3: 
        setCheck("checkbox46", true);
        break;
      case 4: 
        setCheck("checkbox47", true);
      }
      switch (NumberUtil.parseInt(bean.getGoodCooperate(), 1))
      {
      case 1: 
        setCheck("checkbox48", true);
        break;
      case 2: 
        setCheck("checkbox49", true);
        break;
      case 3: 
        setCheck("checkbox50", true);
      }
    }
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    return "";
  }
}
