package com.venus.mc.process.store.level2;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ReturnedMaterialDiaryDetailBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RmsPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("rmsId")))
    {
      int rmsId = NumberUtil.parseInt(request.getParameter("rmsId"), 0);
      ReturnedMaterialStore2FormBean formBean = null;
      ArrayList storeList = null;
      StoreDAO storeDAO = new StoreDAO();
      OrganizationDAO orgDAO = new OrganizationDAO();
      EmployeeDAO empDAO = new EmployeeDAO();
      String templateFileName = request.getSession().getServletContext().getRealPath("/templates/PHIEUTRAVATTU.xls");
      try
      {
        formBean = storeDAO.getReturnedMaterialStore(rmsId);
        storeList = storeDAO.getReturnedMaterialDetails(formBean.getRmsId());
        ReturnedMaterialDiaryDetailBean store = null;
        for (int i = 0; i < storeList.size(); i++)
        {
          store = (ReturnedMaterialDiaryDetailBean)storeList.get(i);
          store.setDetId(++i);
        }
        Map beans = new HashMap();
        StoreBean stoBean = storeDAO.getStore(formBean.getSto2Id());
        OrganizationBean xuong = orgDAO.getOrganization(stoBean.getUsedOrg());
        OrganizationBean to = orgDAO.getOrganization(formBean.getOrgId());
        OrganizationBean project = orgDAO.getOrganization(formBean.getProId());
        EmployeeBean empBean = empDAO.getEmployee(formBean.getCreatedEmp());
        beans.put("mcrp_xuong", xuong.getName());
        beans.put("mcrp_to", to.getName());
        beans.put("mcrp_number", formBean.getRsmNumber());
        beans.put("mcrp_employee", empBean.getFullname());
        beans.put("mcrp_empCode", empBean.getCode());
        beans.put("mcrp_position", empBean.getPosName());
        beans.put("mcrp_project", project.getName());
        Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        beans.put("mcrp_day", DateUtil.formatDate(date, "dd"));
        beans.put("mcrp_month", DateUtil.formatDate(date, "MM"));
        beans.put("mcrp_year", DateUtil.formatDate(date, "yyyy"));
        beans.put("vattu", storeList);
        ExcelExport exporter = new ExcelExport();
        exporter.setBeans(beans);
        long milis = System.currentTimeMillis();
        exporter.export(request, response, templateFileName, "Phieu Tra Vat Tu.xls");
        milis = System.currentTimeMillis() - milis;
        System.out.println("Phieu Tra Vat Tu.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:RmsPrintAction:print-" + ex.getMessage());
      }
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
