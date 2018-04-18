package com.venus.mc.request;

import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
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

public class RequestFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    RequestFormBean formBean = null;
    String reqId = request.getParameter("reqId");
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      reqId = id + "";
    }
    RequestBean reqBean = null;
    if (!GenericValidator.isBlankOrNull(reqId))
    {
      RequestDAO requestDAO = new RequestDAO();
      try
      {
        reqBean = requestDAO.getRequest(Integer.parseInt(reqId));
        if (reqBean != null)
        {
          formBean = new RequestFormBean(reqBean);
          OrganizationDAO orgDAO = new OrganizationDAO();
          String orgIds = MCUtil.getOrganizationID(request.getSession()) + "";
          orgIds = orgIds + "," + orgDAO.getnestedParentOfOrg(orgIds);
          formBean.setAssignedEmp(requestDAO.getRequestAssignedEmp(formBean.getReqId(), orgIds));
          String matIds = requestDAO.getMaterialIdNotCode(formBean.getReqId());
          if (!GenericValidator.isBlankOrNull(matIds)) {
            formBean.setIsMaterialNotCode(1);
          }
        }
        if (formBean.getCreatedEmp() == MCUtil.getMemberID(request.getSession())) {
          formBean.setIsCreatedEmp(1);
        }
      }
      catch (Exception localException1) {}
    }
    if (formBean == null)
    {
      formBean = new RequestFormBean();
      formBean.setEmployeeName(MCUtil.getMemberFullName(session));
      formBean.setCreatedOrg(MCUtil.getOrganizationID(session));
      formBean.setStatusSuggest(1);
      formBean.setStoreOrg(-1);
    }
    ArrayList orgList = null;
    RequestDAO reqDAO = new RequestDAO();
    try
    {
      orgList = reqDAO.getReqOrgs(RequestBean.RQ_XL);
    }
    catch (Exception ex)
    {
      orgList = new ArrayList();
    }
    if (orgList == null) {
      orgList = new ArrayList();
    }
    request.setAttribute("reqOrgXL", orgList);
    try
    {
      orgList = reqDAO.getReqOrgs(RequestBean.RQ_TK);
    }
    catch (Exception ex)
    {
      orgList = new ArrayList();
    }
    if (orgList == null) {
      orgList = new ArrayList();
    }
    request.setAttribute("reqOrgTK", orgList);
    try
    {
      orgList = reqDAO.getReqOrgs(RequestBean.RQ_TT);
    }
    catch (Exception ex)
    {
      orgList = new ArrayList();
    }
    if (orgList == null) {
      orgList = new ArrayList();
    }
    request.setAttribute("reqOrgTT", orgList);
    
    ArrayList arrWhichUse = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.whichUse.project"));
    value.setValue(RequestFormBean.WHICHUSE_PROJECT + "");
    arrWhichUse.add(value);
    
    request.setAttribute("requestWhichUseList", arrWhichUse);
    
    ArrayList arrApprove = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.approve.nottreatyet"));
    value.setValue(RequestFormBean.NOTTREATYET + "");
    arrApprove.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.approve.notapprove"));
    value.setValue(RequestFormBean.NOTAPPROVE + "");
    arrApprove.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.approve.approve"));
    value.setValue(RequestFormBean.APPROVE + "");
    arrApprove.add(value);
    request.setAttribute("requestApproveList", arrApprove);
    
    request.setAttribute("request", formBean);
    
    ArrayList arrOrg = new ArrayList();
    try
    {
      int orgId = 0;
      OrganizationDAO orgDAO = new OrganizationDAO();
      if ((reqBean != null) && (reqBean.getCreatedOrg() > 0))
      {
        orgId = reqBean.getCreatedOrg();
      }
      else
      {
        orgId = MCUtil.getOrganizationID(request.getSession());
        OrganizationBean pB = orgDAO.getParentOrganization(orgId);
        if (pB != null) {
          orgId = pB.getOrgId();
        }
      }
      OrganizationBean bean = orgDAO.getOrganization(orgId);
      if (bean == null) {
        bean = new OrganizationBean();
      }
      arrOrg.add(bean);
    }
    catch (Exception localException2) {}
    if (arrOrg == null) {
      arrOrg = new ArrayList();
    }
    request.setAttribute("orgList", arrOrg);
    
    ArrayList arrOrgHandle = new ArrayList();
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      arrOrgHandle = orgDAO.getOrgByKinds(OrganizationBean.KIND_PROJECT + "," + OrganizationBean.KIND_PHONG);
    }
    catch (Exception localException3) {}
    if (arrOrgHandle == null) {
      arrOrgHandle = new ArrayList();
    }
    request.setAttribute("orgObj", arrOrgHandle);
    
    EmployeeDAO empDAO = new EmployeeDAO();
    ArrayList arrEmp = new ArrayList();
    if (formBean.getReqId() > 0) {
      if (formBean.getAssignedEmp() == MCUtil.getMemberID(request.getSession())) {
        formBean.setIsAssignEmp(1);
      } else {
        try
        {
          if (reqDAO.isAssignRequestOfRequest(MCUtil.getMemberID(request.getSession()), formBean.getReqId()))
          {
            formBean.setIsAssignEmp(1);
            empDAO.setRequestEmp(formBean.getAssignedEmp());
          }
        }
        catch (Exception localException4) {}
      }
    }
    try
    {
      String orgId = "";
      if ((reqBean != null) && (!GenericValidator.isBlankOrNull(reqBean.getOrgHandle()))) {
        orgId = reqBean.getOrgHandle();
      } else {
        orgId = MCUtil.getOrganizationID(request.getSession()) + "";
      }
      if (GenericValidator.isBlankOrNull(orgId)) {
        orgId = MCUtil.getOrganizationID(request.getSession()) + "";
      }
      String orgs = "";
      try
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      }
      catch (Exception localException5) {}
      orgs = orgs + "," + orgId;
      empDAO.setRequestOrg(orgs);
      arrEmp = empDAO.getEmployees();
    }
    catch (Exception localException6) {}
    if (arrEmp == null) {
      arrEmp = new ArrayList();
    }
    arrEmp.add(0, new EmployeeBean());
    request.setAttribute("employeeList", arrEmp);
    if (formBean.getReqId() != 0)
    {
      if (formBean.getCreatedEmp() == MCUtil.getMemberID(session))
      {
        if ((formBean.getCheckApprove() != 1) || (formBean.getStoreApprove() != 1) || (formBean.getPlandepApprove() != 1) || (formBean.getBomApprove() != 1)) {
          request.setAttribute("canSave", "true");
        }
        try
        {
          if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER)) {
            reqDAO.setInvolvedEmps(MCUtil.getMemberID(request.getSession()) + "");
          }
          if (reqDAO.isAssignRequestPosition(MCUtil.getMemberID(session), formBean.getReqId()))
          {
            request.setAttribute("canSave", "true");
            formBean.setIsReceiveRequest(1);
          }
        }
        catch (Exception localException7) {}
      }
      else if (PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK + "," + PermissionUtil.PER_PURCHASING_REQUEST_STORE + "," + PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP + "," + PermissionUtil.PER_PURCHASING_REQUEST_VIEW))
      {
        request.setAttribute("canSave", "true");
        try
        {
          if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER)) {
            reqDAO.setInvolvedEmps(MCUtil.getMemberID(request.getSession()) + "");
          }
          if (reqDAO.isAssignRequestPosition(MCUtil.getMemberID(session), formBean.getReqId())) {
            formBean.setIsReceiveRequest(1);
          }
        }
        catch (Exception localException8) {}
      }
      else
      {
        try
        {
          if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER)) {
            reqDAO.setInvolvedEmps(MCUtil.getMemberID(request.getSession()) + "");
          }
          if (reqDAO.isAssignRequestPosition(MCUtil.getMemberID(session), formBean.getReqId()))
          {
            request.setAttribute("canSave", "true");
            formBean.setIsReceiveRequest(1);
          }
        }
        catch (Exception localException9) {}
      }
    }
    else if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST)) {
      request.setAttribute("canSave", "true");
    }
    if (!GenericValidator.isBlankOrNull(formBean.getPurchaseOrg())) {
      try
      {
        OrganizationDAO dao = new OrganizationDAO();
        OrganizationBean bean = dao.getOrganizations(formBean.getPurchaseOrg());
        formBean.setPurchaseOrgName(bean.getName());
      }
      catch (Exception localException10) {}
    }
    if ((formBean.getCreatedEmp() == MCUtil.getMemberID(request.getSession())) && (formBean.getStatus() == RequestBean.STATUS_CREATE)) {
      formBean.setPermission(RequestBean.STATUS_CREATE);
    } else if (!PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK + "," + PermissionUtil.PER_PURCHASING_REQUEST_STORE + "," + PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP)) {
      formBean.setPermission(RequestBean.STATUS_CREATE);
    } else if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_STORE)) {
      formBean.setPermission(RequestBean.STATUS_STORE);
    } else if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK)) {
      formBean.setPermission(RequestBean.STATUS_CHECK);
    } else if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP)) {
      formBean.setPermission(RequestBean.STATUS_PLANDEP);
    }
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_VIEW)) {
      formBean.setPermission(RequestBean.STATUS_MANAGER);
    }
    if (formBean.getReqId() > 0) {
      try
      {
        String str = reqDAO.getMaterialNullCode(formBean.getReqId());
        if (str.length() > 0) {
          request.setAttribute("canEmail", "true");
        }
      }
      catch (Exception localException11) {}
    }
    ArrayList arrStoreOrg = new ArrayList();
    value = new LabelValueBean();
    value.setLabel("");
    value.setValue("-1");
    arrStoreOrg.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.store.company"));
    value.setValue("0");
    arrStoreOrg.add(value);
    value = new LabelValueBean();
    value.setLabel("Kho P.HCTH");
    value.setValue("2");
    arrStoreOrg.add(value);
    value = new LabelValueBean();
    value.setLabel("Kho X.TBTH");
    value.setValue("19");
    arrStoreOrg.add(value);
    request.setAttribute("storeOrgList", arrStoreOrg);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
