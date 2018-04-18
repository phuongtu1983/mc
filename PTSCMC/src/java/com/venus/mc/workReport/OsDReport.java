package com.venus.mc.workReport;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;

public class OsDReport
  extends SpineReportParser
{
  public OsDReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer osdIdObject = (Integer)object;
    int osdId = osdIdObject.intValue();
    
    MrirDAO mrirDAO = new MrirDAO();
    OsDBean bean = null;
    try
    {
      bean = mrirDAO.getOsD(osdId);
      if (bean == null) {
        return;
      }
      MrirBean mrirBean = mrirDAO.getMrir(bean.getMrirId());
      
      DnBean dnBean = null;
      DnDAO dnDAO = new DnDAO();
      try
      {
        dnBean = dnDAO.getDn(mrirBean.getDnId(), mrirBean.getKind());
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
      if (dnBean != null) {
        setText("mcrp_proName", dnBean.getProName());
      } else {
        setText("mcrp_proName", "");
      }
      setText("mcrp_osdNumber", bean.getOsdNumber());
      setText("mcrp_mrirNumber", mrirBean.getMrirNumber());
      setText("mcrp_createdDate1", bean.getCreatedDate());
      setText("mcrp_createdDate2", bean.getCreatedDate());
      if (!GenericValidator.isBlankOrNull(bean.getNonConform()))
      {
        String[] sNonCon = bean.getNonConform().split(",");
        for (int i = 0; i < sNonCon.length; i++)
        {
          int idx = NumberUtil.parseInt(sNonCon[i], 0);
          switch (idx)
          {
          case 1: 
            setCheck("checkbox1", true);
            break;
          case 2: 
            setCheck("checkbox2", true);
            break;
          case 4: 
            setCheck("checkbox3", true);
            break;
          case 8: 
            setCheck("checkbox4", true);
          }
        }
      }
      ContractDAO conDAO = new ContractDAO();
      VendorDAO venDAO = new VendorDAO();
      if (mrirBean.getConId() > 0)
      {
        ContractBean conBean = conDAO.getContractMrir(mrirBean.getConId());
        VendorBean venBean = venDAO.getVendor(conBean.getVenId());
        setText("mcrp_venName", venBean.getName());
      }
      else
      {
        setText("mcrp_venName", "");
      }
      setText("mcrp_description", bean.getDescription());
      
      EmployeeDAO empDAO = new EmployeeDAO();
      EmployeeBean empBean = null;
      if (bean.getCreatedEmpId() > 0)
      {
        empBean = empDAO.getEmployee(bean.getCreatedEmpId());
        setText("mcrp_createdEmpName", empBean.getFullname());
      }
      else
      {
        setText("mcrp_createdEmpName", "");
      }
      setText("mcrp_correctAct", bean.getCorrectAct());
      if (bean.getCreatedEmpId() > 0)
      {
        empBean = empDAO.getEmployee(bean.getActionBy());
        setText("mcrp_actionBy", empBean.getFullname());
      }
      else
      {
        setText("mcrp_actionBy", "");
      }
      setText("mcrp_dueDate", bean.getDueDate());
      if (bean.getClosed() == 0) {
        setText("mcrp_closed", MCUtil.getBundleString("message.osd.closed1"));
      } else if (bean.getClosed() == 1) {
        setText("mcrp_closed", MCUtil.getBundleString("message.osd.closed2"));
      }
      setText("mcrp_closedDate", bean.getClosedDate());
      setText("mcrp_note", bean.getNote());
    }
    catch (Exception localException1) {}
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    return "";
  }
}
