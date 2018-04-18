package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListRequestDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String reqId = request.getParameter("reqId");
    Integer id = (Integer)session.getAttribute("id");
    if (id != null) {
      reqId = id + "";
    }
    session.removeAttribute("id");
    int empId = MCUtil.getMemberID(request.getSession());
    RequestBean bean = null;
    if (!GenericValidator.isBlankOrNull(reqId)) {
      try
      {
        RequestDAO requestDAO = new RequestDAO();
        int r = Integer.parseInt(reqId);
        bean = requestDAO.getRequest(r);
      }
      catch (Exception localException) {}
    }
    if (bean == null) {
      bean = new RequestBean();
    }
    ArrayList arrEmp = new ArrayList();
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = "";
      try
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      }
      catch (Exception localException1) {}
      orgs = orgs + "," + orgId;
      empDAO.setRequestOrg(orgs);
      arrEmp = empDAO.getEmployees();
    }
    catch (Exception localException2) {}
    if (arrEmp == null) {
      arrEmp = new ArrayList();
    }
    arrEmp.add(0, new EmployeeBean());
    request.setAttribute("employeeList", arrEmp);
    if (!GenericValidator.isBlankOrNull(request.getParameter("assignEmp"))) {
      this.actionForwardResult = "assignSuccess";
    } else if ((bean.getCreatedEmp() == empId) && (bean.getStatus() >= RequestBean.STATUS_HANDLE)) {
      this.actionForwardResult = "assignSuccess";
    }
    ArrayList arrRequestDetail = null;
    if (!GenericValidator.isBlankOrNull(reqId)) {
      try
      {
        RequestDAO requestDAO = new RequestDAO();
        int r = Integer.parseInt(reqId);
        arrRequestDetail = requestDAO.getRequestDetailsForAssign(r);
      }
      catch (Exception localException3) {}
    } else if (!GenericValidator.isBlankOrNull(request.getParameter("detIds"))) {
      try
      {
        RequestDAO requestDAO = new RequestDAO();
        arrRequestDetail = requestDAO.getRequestDetailsForAssign(request.getParameter("detIds"));
      }
      catch (Exception localException4) {}
    }
    if (arrRequestDetail == null) {
      arrRequestDetail = new ArrayList();
    }
    RequestDetailFormBean request_detail = null;
    int isCreatedEmp = 0;
    if (bean.getCreatedEmp() == MCUtil.getMemberID(request.getSession())) {
      isCreatedEmp = 1;
    }
    for (int i = 0; i < arrRequestDetail.size(); i++)
    {
      request_detail = (RequestDetailFormBean)arrRequestDetail.get(i);
      if (request_detail.getEmpId() == empId) {
        request_detail.setIsAssigned(1);
      }
      if ((request_detail.getEmpId() > 0) && 
        (isBelongToDeparment(request_detail.getEmpId(), arrEmp))) {
        request_detail.setIsOtherDepartment(1);
      }
      if ((isCreatedEmp == 1) && (GenericValidator.isBlankOrNull(request_detail.getMatCode()))) {
        request_detail.setCanChangeMaterial(isCreatedEmp);
      }
    }
    request.setAttribute("requestDetailList", arrRequestDetail);
    
    ArrayList arrMaterialKind = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.materialKind.major"));
    value.setValue(RequestFormBean.KIND_MAJOR + "");
    arrMaterialKind.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.request.materialKind.secondary"));
    value.setValue(RequestFormBean.KIND_SECONDARY + "");
    arrMaterialKind.add(value);
    request.setAttribute("requestMaterialKindList", arrMaterialKind);
    return true;
  }
  
  private boolean isBelongToDeparment(int empId, ArrayList employees)
  {
    boolean result = true;
    EmployeeBean employee = null;
    for (int i = 0; i < employees.size(); i++)
    {
      employee = (EmployeeBean)employees.get(i);
      if (empId == employee.getEmpId())
      {
        result = false;
        break;
      }
    }
    return result;
  }
}
