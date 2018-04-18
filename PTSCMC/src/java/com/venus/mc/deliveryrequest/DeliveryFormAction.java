package com.venus.mc.deliveryrequest;

import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.VendorDAO;
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

public class DeliveryFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    DeliveryRequestFormBean formBean = null;
    String reqId = request.getParameter("reqId");
    Integer id = (Integer)session.getAttribute("id");
    if (id != null) {
      reqId = id + "";
    }
    session.removeAttribute("id");
    if (!GenericValidator.isBlankOrNull(reqId))
    {
      ContractDAO contractDAO = new ContractDAO();
      ArrayList arrRequestDetail = null;
      try
      {
        formBean = contractDAO.getDeliveryRequest(Integer.parseInt(reqId));
        arrRequestDetail = contractDAO.getDeliveryRequestDetails(Integer.parseInt(reqId));
      }
      catch (Exception localException1) {}
      if (arrRequestDetail == null) {
        arrRequestDetail = new ArrayList();
      }
      request.setAttribute("deliveryRequestDetailList", arrRequestDetail);
    }
    if (formBean == null)
    {
      formBean = new DeliveryRequestFormBean();
      formBean.setEmployeeName(MCUtil.getMemberFullName(session));
    }
    request.setAttribute("deliveryRequest", formBean);
    ArrayList venList = null;
    String venId = request.getParameter("venId");
    try
    {
      VendorDAO venDAO = new VendorDAO();
      venDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      if (GenericValidator.isBlankOrNull(venId)) {
        venId = "0";
      }
      venList = venDAO.getVendorsHasContract(venId, false);
    }
    catch (Exception ex)
    {
      venList = new ArrayList();
    }
    if (venId == null) {
      venList.add(0, new VendorBean(0));
    }
    request.setAttribute("vendorList", venList);
    String conId = request.getParameter("conId");
    if (!GenericValidator.isBlankOrNull(conId))
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        ContractBean contract = contractDAO.getContract(Integer.parseInt(conId));
        formBean.setVenId(contract.getVenId());
        formBean.setVendorName(contract.getVendorName());
        formBean.setDeliveryDate(contract.getDeliveryDate());
      }
      catch (Exception localException2) {}
    }
    ArrayList arrProcessStatus = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.process.status.notDeliver"));
    value.setValue(ContractFormBean.PROCESS_STATUS_NOTDELIVER + "");
    arrProcessStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.process.status.notEnough"));
    value.setValue(ContractFormBean.PROCESS_STATUS_NOTENOUGH + "");
    arrProcessStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.process.status.deliveried"));
    value.setValue(ContractFormBean.PROCESS_STATUS_DELIVERIED + "");
    arrProcessStatus.add(value);
    request.setAttribute("contractProcessStatusList", arrProcessStatus);
    
    ArrayList arrEmp = new ArrayList();
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      arrEmp = empDAO.getEmployees();
    }
    catch (Exception localException3) {}
    if (arrEmp == null) {
      arrEmp = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmp);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_DELIVERYREQUEST;
  }
}
