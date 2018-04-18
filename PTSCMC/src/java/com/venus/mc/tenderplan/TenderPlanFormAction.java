package com.venus.mc.tenderplan;

import com.venus.mc.bean.BidClosingReportBean;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.CertificateDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.dao.TenderPlanDAO;
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

public class TenderPlanFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    TenderPlanFormBean formBean = null;
    int tenId = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("tenId"))) {
      tenId = Integer.parseInt(request.getParameter("tenId"));
    }
    Integer id = (Integer)session.getAttribute("id");
    if (id != null) {
      tenId = id.intValue();
    }
    if (tenId != 0)
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      try
      {
        TenderPlanBean bean = tenderDAO.getTenderPlan(tenId);
        if (bean != null) {
          formBean = new TenderPlanFormBean(bean);
        }
      }
      catch (Exception localException) {}
    }
    if (formBean == null)
    {
      formBean = new TenderPlanFormBean();
      formBean.setHandleEmp(MCUtil.getMemberID(session));
      formBean.setForm(TenderPlanFormBean.FORM_FAX + "");
      formBean.setEvalKind(TenderPlanFormBean.EVAL_KIND_PART);
    }
    request.setAttribute("tenderPlan", formBean);
    
    ArrayList arrForm = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.form.private"));
    value.setValue(TenderPlanFormBean.FORM_PRIVATE + "");
    arrForm.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.form.fax"));
    value.setValue(TenderPlanFormBean.FORM_FAX + "");
    arrForm.add(value);
    request.setAttribute("tenderPlanForm", arrForm);
    
    ArrayList arrOfferType = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.offer.in"));
    value.setValue(TenderPlanFormBean.OFFER_IN + "");
    arrOfferType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.offer.out"));
    value.setValue(TenderPlanFormBean.OFFER_OUT + "");
    arrOfferType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.offer.inout"));
    value.setValue(TenderPlanFormBean.OFFER_INOUT + "");
    arrOfferType.add(value);
    request.setAttribute("tenderPlanOffer", arrOfferType);
    
    ArrayList arrEvalKind = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.evalKind.all"));
    value.setValue(TenderPlanFormBean.EVAL_KIND_ALL + "");
    arrEvalKind.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.tenderplan.evalKind.part"));
    value.setValue(TenderPlanFormBean.EVAL_KIND_PART + "");
    arrEvalKind.add(value);
    request.setAttribute("tenderPlanEvalKind", arrEvalKind);
    
    ArrayList arrEmployee = null;
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      
      arrEmployee = empDAO.getEmployees();
    }
    catch (Exception localException1) {}
    if (arrEmployee == null) {
      arrEmployee = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmployee);
    if (tenId != 0) {
      try
      {
        if (formBean.getForm().equals(TenderPlanFormBean.FORM_FAX + ""))
        {
          TechEvalDAO teDAO = new TechEvalDAO();
          TechEvalBean bean = teDAO.getTechEvalByTenId(tenId);
          if (bean == null) {
            request.setAttribute("canSave", "true");
          }
        }
        else
        {
          TenderPlanDAO tenderDAO = new TenderPlanDAO();
          BidClosingReportBean bean = tenderDAO.getBidClosingReportByTenId(tenId);
          if (bean == null) {
            request.setAttribute("canSave", "true");
          }
        }
      }
      catch (Exception localException2) {}
    } else {
      request.setAttribute("canSave", "true");
    }
    ArrayList arrCertificate = null;
    try
    {
      CertificateDAO cerDAO = new CertificateDAO();
      arrCertificate = cerDAO.getCertificates();
    }
    catch (Exception localException3) {}
    if (arrCertificate == null) {
      arrCertificate = new ArrayList();
    }
    request.setAttribute("materialCertificateList", arrCertificate);
    if (!GenericValidator.isBlankOrNull(request.getParameter("reqIds")))
    {
      RequestDAO requestDAO = new RequestDAO();
      try
      {
        int assEmployee = requestDAO.getRequestAssignEmployee(request.getParameter("reqIds"));
        if ((assEmployee != 0) && 
          (formBean.getHandleEmp() == 0)) {
          formBean.setHandleEmp(assEmployee);
        }
      }
      catch (Exception localException4) {}
    }
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_TENDERPLAN;
  }
}
