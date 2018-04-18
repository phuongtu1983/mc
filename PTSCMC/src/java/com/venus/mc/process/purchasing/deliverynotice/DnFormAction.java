package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class DnFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    DnBean bean = null;
    int orgId = 0;
    String dnId = request.getParameter("dnId");
    
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 1);
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      dnId = id + "";
    }
    DnDAO dnDAO = new DnDAO();
    if (!GenericValidator.isBlankOrNull(dnId))
    {
      ArrayList arrDnDetail = null;
      try
      {
        bean = dnDAO.getDn(NumberUtil.parseInt(dnId, 0), kind);
        bean.setCanSave(dnDAO.checkMrirNumber(NumberUtil.parseInt(dnId, 0)));
        arrDnDetail = dnDAO.getDnDetails(Integer.parseInt(dnId));
      }
      catch (Exception localException) {}
      if (arrDnDetail == null) {
        arrDnDetail = new ArrayList();
      }
      request.setAttribute("dnDetailList", arrDnDetail);
    }
    if (bean == null)
    {
      bean = new DnBean();
      bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      bean.setExpectedDate(DateUtil.today("dd/MM/yyyy"));
      
      bean.setCreatedOrg(MCUtil.getOrganizationID(session));
      bean.setCreatedEmp(MCUtil.getMemberID(session));
      bean.setKind(kind);
      bean.setOrgHandle(4);
      bean.setCanSave(1);
      String prefix = DateUtil.today("yyyy");
      try
      {
        if (kind == 2)
        {
          prefix = prefix + "-YCKT";
          bean.setDnNumber(dnDAO.getNextDn2Number(prefix) + "-" + prefix);
        }
        else
        {
          bean.setDnNumber(dnDAO.getNextDnNumber(prefix) + "-" + prefix);
        }
      }
      catch (Exception localException1) {}
    }
    request.setAttribute("dn", bean);
    
    ArrayList arrWhichUse = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.select"));
    value.setValue("0");
    arrWhichUse.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.conId1"));
    value.setValue("1");
    arrWhichUse.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.conId2"));
    value.setValue("2");
    arrWhichUse.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.conId3"));
    value.setValue("3");
    arrWhichUse.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dn.drId"));
    value.setValue("4");
    arrWhichUse.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.conId4"));
    value.setValue("5");
    arrWhichUse.add(value);
    
    request.setAttribute("dnWhichUseList", arrWhichUse);
    
    ArrayList arrStatus = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.select"));
    value.setValue("0");
    arrStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dn.status1"));
    value.setValue("1");
    arrStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dn.status2"));
    value.setValue("2");
    arrStatus.add(value);
    request.setAttribute("dnStatusList", arrStatus);
    
    ArrayList arrOrg = new ArrayList();
    
    OrganizationBean org = null;
    if (bean.getDnId() > 0) {
      try
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        org = orgDAO.getOrganization(bean.getCreatedOrg());
      }
      catch (Exception localException2) {}
    } else {
      try
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        orgId = MCUtil.getOrganizationID(request.getSession());
        OrganizationBean pB = orgDAO.getParentOrganization(orgId);
        if (pB != null) {
          orgId = pB.getOrgId();
        }
        org = orgDAO.getOrganization(orgId);
      }
      catch (Exception localException3) {}
    }
    if (org == null) {
      org = new OrganizationBean();
    }
    value = new LabelValueBean(String.valueOf(StringUtil.decodeString(org.getName())), String.valueOf(org.getOrgId()));
    arrOrg.add(value);
    request.setAttribute("orgList", arrOrg);
    
    ArrayList orgList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      orgList = orgDAO.getOrgByDN();
    }
    catch (Exception localException4) {}
    arrOrg = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.organization.select"));
    value.setValue("0");
    arrOrg.add(value);
    for (int i = 0; i < orgList.size(); i++)
    {
      OrganizationBean orgProcess = (OrganizationBean)orgList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(orgProcess.getName())));
      value.setValue(String.valueOf(orgProcess.getOrgId()));
      arrOrg.add(value);
    }
    request.setAttribute("organizationList", arrOrg);
    
    ArrayList proList = null;
    try
    {
      ProjectDAO proDAO = new ProjectDAO();
      if (kind == 2) {
        proList = proDAO.getProjectsForDn2(orgId);
      } else {
        proList = proDAO.getProjectsForDn4();
      }
    }
    catch (Exception localException5) {}
    ArrayList arrPro = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.project.select"));
    value.setValue("0");
    arrPro.add(value);
    for (int i = 0; i < proList.size(); i++)
    {
      ProjectBean pro = (ProjectBean)proList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(pro.getName())));
      value.setValue(String.valueOf(pro.getProId()));
      arrPro.add(value);
    }
    request.setAttribute("proList", arrPro);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
}
