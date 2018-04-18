package com.venus.mc.contract;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderPlanDAO;
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

public class ContractFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    ContractFormBean formBean = null;
    ArrayList arrContractDetail = null;
    int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
    int parentId = NumberUtil.parseInt(request.getParameter("parentId"), 0);
    String detIds = request.getParameter("detIds");
    session.setAttribute("detIds", detIds);
    String conIds = request.getParameter("conIds");
    String conId = request.getParameter("conId");
    String formKind = request.getParameter("formKind");
    if (GenericValidator.isBlankOrNull(formKind)) {
      formKind = "";
    }
    String s = "";
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("stt");
    session.setAttribute("stt", Integer.valueOf(0));
    if (id != null) {
      conId = id + "";
    }
    session.removeAttribute("id");
    ContractDAO contractDAO = new ContractDAO();
    if (!GenericValidator.isBlankOrNull(conId)) {
      try
      {
        ContractBean bean = contractDAO.getContract(Integer.parseInt(conId));
        if (bean != null)
        {
          formBean = new ContractFormBean(bean);
          if (bean.getKind() == ContractBean.KIND_DELIVERY_REQUEST)
          {
            arrContractDetail = contractDAO.getContractDetailsDeliveryRequest(bean.getConId());
          }
          else if (bean.getKind() == ContractBean.KIND_ADJUSTMENT)
          {
            arrContractDetail = contractDAO.getContractDetailsForAdjustments(bean.getConId());
          }
          else if (bean.getKind() == ContractBean.KIND_APPENDIX)
          {
            String temp = formBean.getContractNumber();
            int ind = temp.indexOf("PL ");
            if (ind > -1)
            {
              temp = temp.substring(ind + "PL ".length());
              ind = temp.indexOf(" H");
              if (ind > -1) {
                temp = temp.substring(0, ind);
              }
            }
            formBean.setContractNumber(temp);
            arrContractDetail = contractDAO.getContractDetailsForAppendixs(bean.getConId());
          }
          else
          {
            arrContractDetail = contractDAO.getContractDetails(bean.getConId());
          }
          s = contractDAO.getContractDetailsA(bean.getConId());
          request.setAttribute("matIds", s.split(";")[0]);
          request.setAttribute("reqIds", s.split(";")[1]);
          request.setAttribute("detIds", s.split(";")[2]);
          ArrayList arrCost = contractDAO.getContractCosts(bean.getConId());
          if (arrCost != null) {
            request.setAttribute("contractProjectList", arrCost);
          }
          ArrayList arrInvoice = contractDAO.getContractInvoices(bean.getConId());
          if (arrInvoice != null) {
            request.setAttribute("contractBillList", arrInvoice);
          }
        }
      }
      catch (Exception localException1) {}
    }
    if (formBean == null)
    {
      formBean = new ContractFormBean();
      formBean.setPaymentStatus(ContractFormBean.STATUS_APPROVING);
      formBean.setEmployeeName(MCUtil.getMemberFullName(session));
      formBean.setPaymentMode(ContractFormBean.PAYMENT_TRANSFER);
      formBean.setTenId(tenId);
      formBean.setTransport(0.0D);
      formBean.setOtherFee(0.0D);
      formBean.setTotal(0.0D);
      formBean.setTotalNotVAT(0.0D);
      formBean.setSumVAT(0.0D);
      formBean.setFollowEmp(MCUtil.getMemberID(request.getSession()));
      try
      {
        formBean.setCurrency(contractDAO.getCurrency(tenId));
        
        formBean.setDelivery(contractDAO.getDeliveryTime(tenId));
      }
      catch (Exception localException2) {}
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
        formBean.setIsPermissionPrice(1);
      }
      try
      {
        String prefix = MCUtil.getMemberName(session) + "_" + DateUtil.today("dd_MM") + "_";
        String number = "";
        int kind = 0;
        if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
          kind = Integer.parseInt(request.getParameter("kind"));
        }
        ContractBean pC = null;
        if ((!formKind.equals("notincontract")) && 
          (!GenericValidator.isBlankOrNull(conIds)))
        {
          String[] cs = conIds.split(",");
          int c = 0;
          for (int i = 0; i < cs.length; i++)
          {
            c = NumberUtil.parseInt(cs[i], 0);
            if (c > 0) {
              break;
            }
          }
          if (c > 0)
          {
            pC = contractDAO.getContract(c);
            
            formBean.setCurrency(pC.getCurrency());
            formBean.setDelivery(pC.getDelivery());
            formBean.setCertificate(pC.getCertificate());
          }
        }
        if (kind == ContractBean.KIND_APPENDIX)
        {
          String conNumber = "";
          if (pC != null)
          {
            conNumber = pC.getContractNumber();
            
            conNumber = " " + MCUtil.getBundleString("message.contract.abb") + " " + conNumber;
          }
          number = contractDAO.getNextContractNumberWithSubfixForAppendix(conNumber, 2);
          
          formBean.setContractNumber(number);
        }
        else if (kind == ContractBean.KIND_ADJUSTMENT)
        {
          String conNumber = "";
          if (pC != null)
          {
            conNumber = pC.getContractNumber();
            int ind = conNumber.indexOf("-");
            if (ind > -1) {
              conNumber = conNumber.substring(0, ind);
            }
            conNumber = " H?? " + conNumber;
          }
          number = contractDAO.getNextContractNumberWithSubfix(conNumber, 2);
          formBean.setContractNumber(number + conNumber);
        }
        else
        {
          number = contractDAO.getNextContractNumber(prefix, 4);
          formBean.setContractNumber(prefix + number);
        }
        request.setAttribute("matIds", Integer.valueOf(0));
        request.setAttribute("reqIds", Integer.valueOf(0));
        request.setAttribute("detIds", Integer.valueOf(0));
      }
      catch (Exception localException3) {}
    }
    else if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      try
      {
        if (formBean.getConId() == 0)
        {
          formBean.setIsPermissionPrice(1);
        }
        else
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          int orgId = MCUtil.getOrganizationID(request.getSession());
          String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
          orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
          orgs = orgs + "," + orgId;
          contractDAO.setRequestOrg(orgs);
          contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
          if (contractDAO.isPermissionOnContractInfo(formBean.getConId()) > 0) {
            formBean.setIsPermissionPrice(1);
          }
        }
      }
      catch (Exception localException4) {}
    }
    if ((formBean.getKind() == 0) && 
      (!GenericValidator.isBlankOrNull(request.getParameter("kind")))) {
      formBean.setKind(Integer.parseInt(request.getParameter("kind")));
    }
    if (formBean.getKind() == ContractBean.KIND_ORDER) {
      if (!GenericValidator.isBlankOrNull(request.getParameter("isnotresell"))) {
        formBean.setIsNotResell(1);
      } else if (formBean.getTenId() > 0) {
        formBean.setIsNotResell(1);
      } else if (formBean.getParentId() > 0) {
        formBean.setIsNotResell(1);
      }
    }
    if (formBean.getCurrency() != null) {
      formBean.setCurrency(formBean.getCurrency().replace("null", ""));
    }
    if (formBean.getDelivery() != null) {
      formBean.setDelivery(formBean.getDelivery().replace("null", ""));
    }
    if (formBean.getCertificate() != null) {
      formBean.setCertificate(formBean.getCertificate().replace("null", ""));
    }
    request.setAttribute("contract", formBean);
    if (arrContractDetail == null) {
      arrContractDetail = new ArrayList();
    }
    request.setAttribute("contractDetailList", arrContractDetail);
    
    ArrayList arrStatus = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.status.approved"));
    value.setValue(ContractFormBean.STATUS_APPROVED + "");
    arrStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.status.approving"));
    value.setValue(ContractFormBean.STATUS_APPROVING + "");
    arrStatus.add(value);
    
    request.setAttribute("statusList", arrStatus);
    
    ArrayList arrMaterialStatus = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.empty"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_NORMAL + "");
    arrMaterialStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.cancel"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
    arrMaterialStatus.add(value);
    request.setAttribute("principleMaterialKindList", arrMaterialStatus);
    
    ArrayList arrMaterialCancelStatus = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.cancel"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
    arrMaterialCancelStatus.add(value);
    request.setAttribute("principleMaterialCancelKindList", arrMaterialCancelStatus);
    
    ArrayList arrProcessStatus = new ArrayList();
    value = new LabelValueBean();
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
    
    ArrayList arrPayment = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.paymentMode.transfer"));
    value.setValue(ContractFormBean.PAYMENT_TRANSFER + "");
    arrPayment.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.contract.paymentMode.cash"));
    value.setValue(ContractFormBean.PAYMENT_CASH + "");
    arrPayment.add(value);
    request.setAttribute("paymentModeList", arrPayment);
    if (formBean.getKind() == ContractBean.KIND_CONTRACT)
    {
      this.actionForwardResult = "contract";
      if (formBean.getConId() == 0)
      {
        ArrayList arrTenderPlan = null;
        try
        {
          TenderPlanDAO tenderDAO = new TenderPlanDAO();
          arrTenderPlan = tenderDAO.getTenderPlansForContract();
        }
        catch (Exception localException5) {}
        if (arrTenderPlan == null) {
          arrTenderPlan = new ArrayList();
        }
        TenderPlanBean tender = new TenderPlanBean(0);
        tender.setTenderNumber("");
        arrTenderPlan.add(0, tender);
        request.setAttribute("tenderPlanList", arrTenderPlan);
      }
    }
    else if (formBean.getKind() == ContractBean.KIND_PRINCIPLE)
    {
      this.actionForwardResult = "contractPrinciple";
      if (formBean.getConId() == 0)
      {
        ArrayList arrTenderPlan = null;
        try
        {
          TenderPlanDAO tenderDAO = new TenderPlanDAO();
          arrTenderPlan = tenderDAO.getTenderPlansForContract();
        }
        catch (Exception localException6) {}
        if (arrTenderPlan == null) {
          arrTenderPlan = new ArrayList();
        }
        TenderPlanBean tender = new TenderPlanBean(0);
        tender.setTenderNumber("");
        arrTenderPlan.add(0, tender);
        request.setAttribute("tenderPlanList", arrTenderPlan);
      }
    }
    else if (formBean.getKind() == ContractBean.KIND_ORDER)
    {
      this.actionForwardResult = "order";
      ArrayList arrSource = new ArrayList();
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.select"));
      value.setValue("");
      arrSource.add(value);
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.order"));
      value.setValue("contract");
      arrSource.add(value);
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.tenderplan"));
      value.setValue("tenderplan");
      arrSource.add(value);
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.retail"));
      value.setValue("other");
      arrSource.add(value);
      request.setAttribute("orderSource", arrSource);
      if (formBean.getConId() != 0)
      {
        formBean.setOrderMaterialSource("other");
        try
        {
          String matIds = contractDAO.getMaterialForEmailNon(formBean.getConId());
          if (!matIds.equals("0")) {
            request.setAttribute("canEmail", "true");
          }
        }
        catch (Exception localException7) {}
        if (formBean.getParentId() != 0)
        {
          try
          {
            ContractBean parent = contractDAO.getContract(formBean.getParentId());
            request.setAttribute("orderContract", parent);
            ArrayList materialList = null;
            materialList = contractDAO.getContractMaterials(formBean.getParentId() + "");
            if (materialList == null) {
              materialList = new ArrayList();
            }
            request.setAttribute("orderMaterial", materialList);
          }
          catch (Exception localException8) {}
          formBean.setOrderMaterialSource("contract");
        }
        else if (formBean.getTenId() != 0)
        {
          ArrayList materialList = null;
          try
          {
            materialList = contractDAO.getMaterialOfVendorInTenderPlan(formBean.getTenId() + "", formBean.getVenId() + "");
          }
          catch (Exception localException9) {}
          if (materialList == null) {
            materialList = new ArrayList();
          }
          request.setAttribute("orderMaterial", materialList);
          formBean.setOrderMaterialSource("tenderplan");
        }
        else
        {
          ArrayList venList = null;
          String venId = request.getParameter("venId");
          try
          {
            VendorDAO venDAO = new VendorDAO();
            venDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
            if (GenericValidator.isBlankOrNull(venId)) {
              venId = "0";
            }
            boolean isExpire = false;
            if (!GenericValidator.isBlankOrNull(request.getParameter("expire"))) {
              isExpire = true;
            }
            venList = venDAO.getVendorsHasContract(venId, isExpire);
          }
          catch (Exception ex)
          {
            venList = new ArrayList();
          }
          if (venId == null) {
            venList.add(0, new VendorBean(0));
          }
          if (formBean.getVenId() > 0)
          {
            VendorBean vBean = new VendorBean(formBean.getVenId());
            vBean.setName(formBean.getVendorName());
            venList.add(vBean);
          }
          request.setAttribute("vendorList", venList);
        }
      }
      else
      {
        String materialCon = request.getParameter("materialCon");
        if (!GenericValidator.isBlankOrNull(materialCon)) {
          try
          {
            ContractBean parent = contractDAO.getContract(Integer.parseInt(materialCon));
            request.setAttribute("orderContract", parent);
          }
          catch (Exception localException10) {}
        }
      }
    }
    else if (formBean.getKind() == ContractBean.KIND_APPENDIX)
    {
      this.actionForwardResult = "appendix";
      try
      {
        if (!GenericValidator.isBlankOrNull(request.getParameter("parentId")))
        {
          ContractBean bean = contractDAO.getContract(NumberUtil.parseInt(request.getParameter("parentId"), 0));
          if (formBean != null)
          {
            formBean.setParentId(bean.getConId());
            formBean.setParentNumber(bean.getContractNumber());
          }
        }
      }
      catch (Exception localException11) {}
    }
    else if (formBean.getKind() == ContractBean.KIND_ADJUSTMENT)
    {
      this.actionForwardResult = "adjustment";
      try
      {
        String matIds = contractDAO.getMaterialForEmailNonAdjustment(formBean.getConId());
        if (!matIds.equals("0")) {
          request.setAttribute("canEmail", "true");
        }
      }
      catch (Exception localException12) {}
      try
      {
        if ((!GenericValidator.isBlankOrNull(request.getParameter("parentId"))) && (formBean.getConId() == 0))
        {
          ContractBean bean = contractDAO.getContract(NumberUtil.parseInt(request.getParameter("parentId"), 0));
          if (formBean != null)
          {
            formBean = new ContractFormBean(bean);
            formBean.setConId(0);
            formBean.setContractNumber("");
            formBean.setAppendixContractNumber("");
            formBean.setPaymentStatus(2);
            formBean.setParentId(bean.getConId());
            formBean.setParentNumber(bean.getContractNumber());
            formBean.setKind(ContractBean.KIND_ADJUSTMENT);
            request.setAttribute("contract", formBean);
          }
        }
      }
      catch (Exception localException13) {}
    }
    else if (formBean.getKind() == ContractBean.KIND_DELIVERY_REQUEST)
    {
      ArrayList venList = null;
      String venId = request.getParameter("venId");
      try
      {
        VendorDAO venDAO = new VendorDAO();
        venDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        if (GenericValidator.isBlankOrNull(venId)) {
          venId = "0";
        }
        boolean isExpire = false;
        if (!GenericValidator.isBlankOrNull(request.getParameter("expire"))) {
          isExpire = true;
        }
        venList = venDAO.getVendorsHasContract(venId, isExpire);
      }
      catch (Exception ex)
      {
        venList = new ArrayList();
      }
      if (venId == null) {
        venList.add(0, new VendorBean(0));
      }
      request.setAttribute("vendorList", venList);
      this.actionForwardResult = "deliveryrequest";
    }
    ArrayList arrEmp = new ArrayList();
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      empDAO.setRequestOrg(orgs);
      arrEmp = empDAO.getEmployees();
    }
    catch (Exception localException14) {}
    if (arrEmp == null) {
      arrEmp = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmp);
    
    ArrayList currency = null;
    if ((parentId > 0) || (formBean.getCurrency() == null))
    {
      currency = MCUtil.getArrayCurrency();
    }
    else
    {
      currency = new ArrayList();
      currency.add(new LabelValueBean(formBean.getCurrency(), formBean.getCurrency()));
    }
    if ((formBean.getCurrency() != null) && (
      (formBean.getCurrency().length() == 0) || (formBean.getPaymentStatus() != ContractFormBean.STATUS_APPROVED))) {
      currency = MCUtil.getArrayCurrency();
    }
    if ((formBean.getOrderMaterialSource() != null) && 
      (formBean.getCurrency() != null) && (
      (formBean.getCurrency().length() == 0) || (formBean.getOrderMaterialSource().equals("other")))) {
      currency = MCUtil.getArrayCurrency();
    }
    request.setAttribute("currencyList", currency);
    if (((formBean.getFollowEmp() == MCUtil.getMemberID(request.getSession())) && (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED)) || ((formBean.getResponseEmp() == MCUtil.getMemberID(request.getSession())) && (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVING)) || (formBean.getConId() == 0)) {
      request.setAttribute("canSave", "true");
    }
    if (((formBean.getFollowEmp() == MCUtil.getMemberID(request.getSession())) && (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED)) || ((formBean.getResponseEmp() == MCUtil.getMemberID(request.getSession())) && (formBean.getPaymentStatus() == ContractFormBean.STATUS_APPROVED)) || (formBean.getConId() == 0)) {
      request.setAttribute("expireDate", "true");
    }
    ArrayList arr = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.select"));
    value.setValue("0");
    arr.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.confirm"));
    value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
    arr.add(value);
    
    request.setAttribute("evalTbeList", arr);
    if (formBean.getConId() > 0) {
      try
      {
        String matIds = contractDAO.getMaterialForEmail(formBean.getConId());
        if (!matIds.equals("0")) {
          request.setAttribute("canEmail", "true");
        }
      }
      catch (Exception localException15) {}
    }
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
