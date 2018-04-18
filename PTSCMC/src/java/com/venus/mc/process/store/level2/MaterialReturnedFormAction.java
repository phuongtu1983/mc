package com.venus.mc.process.store.level2;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.bean.StoreLevel2Bean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialReturnedFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    ReturnedMaterialStore2FormBean formBean = null;
    String rmsId = request.getParameter("rmsId");
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      rmsId = id + "";
    }
    StoreDAO storeDAO = new StoreDAO();
    ArrayList storeList = null;
    if (!GenericValidator.isBlankOrNull(rmsId)) {
      try
      {
        formBean = storeDAO.getReturnedMaterialStore(Integer.parseInt(rmsId));
        storeList = storeDAO.getReturnedMaterialDetails(formBean.getRmsId());
      }
      catch (Exception localException) {}
    }
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    if (formBean == null) {
      try
      {
        if (formBean != null)
        {
          storeList = storeDAO.getReturnedMaterialDetails(formBean.getRmsId());
        }
        else
        {
          StoreLevel2Bean storeBean = storeDAO.getStoreLevel2(stoId);
          formBean = new ReturnedMaterialStore2FormBean();
          formBean.setOrganizationName(storeBean.getName());
          formBean.setSto2Id(storeBean.getSto2Id());
          formBean.setUpdateDate(DateUtil.today("dd/MM/yyyy"));
          
          String prefix = "";
          OrganizationDAO orgDAO = new OrganizationDAO();
          OrganizationBean uo = orgDAO.getOrganization(storeBean.getUsedOrg());
          OrganizationBean ob = orgDAO.getOrganization(storeBean.getOrgId());
          if (uo != null) {
            prefix = prefix + uo.getAbbreviate() + "-";
          } else {
            prefix = prefix + "XXX-";
          }
          if (ob != null) {
            prefix = prefix + ob.getAbbreviate() + "-";
          } else {
            prefix = prefix + "YYY-";
          }
          prefix = prefix + DateUtil.today("yyyy") + "-";
          String number = storeDAO.getNextRMSNumber(prefix, 4);
          prefix = prefix + number;
          formBean.setRsmNumber(prefix);
        }
      }
      catch (Exception localException1) {}
    } else {
      stoId = formBean.getSto2Id();
    }
    request.setAttribute("returnedMaterialStore", formBean);
    if (storeList == null) {
      storeList = new ArrayList();
    }
    request.setAttribute("rmsDetailList", storeList);
    
    ArrayList arrPro = new ArrayList();
    ArrayList arrOrg = null;
    if (stoId > 0) {
      try
      {
        StoreBean stoBean = storeDAO.getStore(stoId);
        if (stoBean != null)
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          OrganizationBean orgBean = orgDAO.getOrganization(stoBean.getProId());
          arrPro.add(orgBean);
          if (formBean.getRmsId() == 0)
          {
            arrOrg = orgDAO.getOrganizationChild(stoBean.getUsedOrg());
          }
          else
          {
            arrOrg = new ArrayList();
            OrganizationBean organization = orgDAO.getOrganization(formBean.getOrgId());
            arrOrg.add(organization);
          }
        }
      }
      catch (Exception localException2) {}
    }
    request.setAttribute("projectList", arrPro);
    if (arrOrg == null) {
      arrOrg = new ArrayList();
    }
    request.setAttribute("orgList", arrOrg);
    
    ArrayList arrEmployee = null;
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      if (formBean.getRmsId() == 0)
      {
        String orgs = "0";
        OrganizationBean organization = null;
        int i = 0;
        if (i < arrOrg.size())
        {
          organization = (OrganizationBean)arrOrg.get(i);
          orgs = orgs + "," + organization.getOrgId();
        }
        arrEmployee = empDAO.getEmployeeByOrgIds(orgs);
      }
      else
      {
        arrEmployee = new ArrayList();
        EmployeeBean empBean = empDAO.getEmployee(formBean.getCreatedEmp());
        arrEmployee.add(empBean);
      }
    }
    catch (Exception localException3) {}
    if (arrEmployee == null) {
      arrEmployee = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmployee);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_STORE2;
  }
}
