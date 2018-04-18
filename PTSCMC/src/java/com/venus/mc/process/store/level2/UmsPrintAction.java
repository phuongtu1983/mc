package com.venus.mc.process.store.level2;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.bean.UsedMaterialDiaryDetailBean;
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

public class UmsPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("umsId")))
    {
      int umsId = NumberUtil.parseInt(request.getParameter("umsId"), 0);
      UsedMaterialStore2FormBean formBean = null;
      ArrayList storeList = null;
      StoreDAO storeDAO = new StoreDAO();
      OrganizationDAO orgDAO = new OrganizationDAO();
      EmployeeDAO empDAO = new EmployeeDAO();
      String templateFileName = request.getSession().getServletContext().getRealPath("/templates/PhieuNhanVatTu.xls");
      try
      {
        formBean = storeDAO.getUsedMaterialStore(umsId);
        storeList = storeDAO.getUsedMaterialDetails(formBean.getUmsId());
        UsedMaterialDiaryDetailBean store = null;
        int count = 1;
        for (int i = 0; i < storeList.size(); i++)
        {
          store = (UsedMaterialDiaryDetailBean)storeList.get(i);
          store.setDetId(count++);
        }
        Map beans = new HashMap();
        StoreBean stoBean = storeDAO.getStore(formBean.getSto2Id());
        OrganizationBean xuong = orgDAO.getOrganization(stoBean.getUsedOrg());
        OrganizationBean to = orgDAO.getOrganization(formBean.getOrgId());
        OrganizationBean project = orgDAO.getOrganization(formBean.getProId());
        EmployeeBean empBean = empDAO.getEmployee(formBean.getCreatedEmp());
        beans.put("mcrp_xuong", xuong.getName());
        beans.put("mcrp_to", to.getName());
        beans.put("mcrp_number", formBean.getUsmNumber());
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
        exporter.export(request, response, templateFileName, "Phieu Linh Vat Tu.xls");
        milis = System.currentTimeMillis() - milis;
        System.out.println("Phieu Linh Vat Tu.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:UmsPrintAction:print-" + ex.getMessage());
      }
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
