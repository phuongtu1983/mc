package com.venus.mc.process.store.mrv;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MrvFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MrvFormBean formBean = null;
    HttpSession session = request.getSession();
    String mrvId = request.getParameter("mrvId");
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      mrvId = id + "";
    }
    if (!GenericValidator.isBlankOrNull(mrvId))
    {
      MsvDAO msvDAO = new MsvDAO();
      try
      {
        MsvBean bean = msvDAO.getMsv(Integer.parseInt(mrvId));
        if (bean != null) {
          formBean = new MrvFormBean(bean);
        }
      }
      catch (Exception localException1) {}
      ArrayList arrMaterial = null;
      try
      {
        arrMaterial = msvDAO.getMaterialsFromMsv(Integer.parseInt(mrvId));
      }
      catch (Exception localException2) {}
      request.setAttribute("materialList", arrMaterial);
    }
    else
    {
      int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
      if (mrirId > 0)
      {
        MsvDAO msvDAO = new MsvDAO();
        
        ArrayList arrMaterial = null;
        try
        {
          arrMaterial = msvDAO.getMaterialsFromMrir(mrirId, 0);
        }
        catch (Exception localException3) {}
        request.setAttribute("materialList", arrMaterial);
      }
      formBean = new MrvFormBean();
      formBean.setMrirId(mrirId);
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
      formBean.setStoId(1);
      formBean.setStoName(MCUtil.getBundleString("message.store.company"));
      ArrayList arrMrir = null;
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        arrMrir = mrirDAO.getMrir4Mrv();
      }
      catch (Exception ex)
      {
        Logger.getLogger(MrvFormAction.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (arrMrir == null) {
        arrMrir = new ArrayList();
      }
      request.setAttribute("mrirList", arrMrir);
    }
    if (formBean == null)
    {
      formBean = new MrvFormBean();
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
    }
    request.setAttribute("mrvObj", formBean);
    OrganizationDAO orgDAO = new OrganizationDAO();
    try
    {
      ArrayList orgList = orgDAO.getOrganizationList();
      orgList.add(0, new LabelValueBean(MCUtil.getBundleString("message.organization.select"), "0"));
      request.setAttribute("orgList", orgList);
    }
    catch (Exception localException4) {}
    try
    {
      ArrayList proList = orgDAO.getProjectList();
      proList.add(0, new LabelValueBean(MCUtil.getBundleString("message.project.select"), "0"));
      request.setAttribute("proList", proList);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    EmployeeDAO empDAO = new EmployeeDAO();
    try
    {
      ArrayList empList = empDAO.getEmployeeListByOrg(formBean.getOrgId());
      empList.add(0, new LabelValueBean(MCUtil.getBundleString("message.employee.select"), "0"));
      request.setAttribute("empList", empList);
    }
    catch (Exception localException5) {}
    return true;
  }
  
  protected String getXmlMessage()
  {
    return "mrvForm";
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("mrvId"))) {
      return request.getParameter("mrvId");
    }
    return "";
  }
}
