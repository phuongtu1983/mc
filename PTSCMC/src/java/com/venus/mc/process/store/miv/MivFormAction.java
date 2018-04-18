package com.venus.mc.process.store.miv;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.process.store.rfm.RfmFormBean;
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

public class MivFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MivFormBean formBean = null;
    HttpSession session = request.getSession();
    String mivId = request.getParameter("mivId");
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
      kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    }
    if (kind == 0)
    {
      kind = ((Integer)session.getAttribute("kind")).intValue();
      session.removeAttribute("kind");
    }
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      mivId = id + "";
    }
    MivDAO mivDAO = new MivDAO();
    if (!GenericValidator.isBlankOrNull(mivId)) {
      try
      {
        MivBean bean = null;
        if (kind == MivBean.KIND_COMPANY) {
          bean = mivDAO.getMiv(Integer.parseInt(mivId));
        } else {
          bean = mivDAO.getEMiv(Integer.parseInt(mivId));
        }
        if (bean != null)
        {
          formBean = new MivFormBean(bean);
          formBean.setMivKind(kind);
        }
      }
      catch (Exception localException) {}
    }
    if (formBean == null)
    {
      formBean = new MivFormBean();
      formBean.setCreatorName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreator(MCUtil.getMemberID(request.getSession()));
      formBean.setMivKind(kind);
      formBean.setMivNumber("CKHH-MIV-XXX-YYYY");
    }
    else
    {
      ArrayList arrDetail = null;
      try
      {
        if (kind == MivBean.KIND_COMPANY) {
          arrDetail = mivDAO.getMivDetails(formBean.getMivId());
        } else {
          arrDetail = mivDAO.getEMivDetails(formBean.getMivId());
        }
      }
      catch (Exception localException1) {}
      if (arrDetail != null) {
        request.setAttribute("mivDetailList", arrDetail);
      }
    }
    request.setAttribute("miv", formBean);
    
    ArrayList arrEmployee = null;
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      if (formBean.getMivId() != 0)
      {
        RfmDAO rfmDao = new RfmDAO();
        RfmBean rfmBean = rfmDao.getRfm(formBean.getRfmId(), 0, MCUtil.getMemberID(request.getSession()));
        arrEmployee = empDAO.getEmployeeListByOrg(rfmBean.getRequestOrg());
      }
    }
    catch (Exception localException2) {}
    if (arrEmployee == null) {
      arrEmployee = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmployee);
    
    ArrayList arrRfm = null;
    try
    {
      RfmDAO rfmDAO = new RfmDAO();
      if (formBean.getMivKind() == MivBean.KIND_COMPANY) {
        arrRfm = rfmDAO.getRfmsAvailable(0, 0);
      } else {
        arrRfm = rfmDAO.getERfmsAvailable();
      }
    }
    catch (Exception localException3) {}
    if (arrRfm == null) {
      arrRfm = new ArrayList();
    }
    RfmFormBean rfmBean = new RfmFormBean();
    rfmBean.setRfmNumber("");
    arrRfm.add(0, rfmBean);
    request.setAttribute("rfmList", arrRfm);
    
    ArrayList arrType = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.miv.type.other"));
    value.setValue(MivBean.TYPE_OTHER + "");
    arrType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.miv.type.ccdc"));
    value.setValue(MivBean.TYPE_CCDC + "");
    arrType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.miv.type.tscdhh"));
    value.setValue(MivBean.TYPE_TSCDHH + "");
    arrType.add(value);
    request.setAttribute("mivTypeList", arrType);
    
    ArrayList orgList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      orgList = orgDAO.getOrgByKinds("0,1,2,3,100");
    }
    catch (Exception localException4) {}
    ArrayList arrOrg = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.organization.select"));
    
    value.setValue("0");
    arrOrg.add(value);
    for (int i = 0; i < orgList.size(); i++)
    {
      OrganizationBean org = (OrganizationBean)orgList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(org.getName())));
      value.setValue(String.valueOf(org.getOrgId()));
      arrOrg.add(value);
    }
    request.setAttribute("orgList", arrOrg);
    
    ArrayList proList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      proList = orgDAO.getOrgByKind(OrganizationBean.KIND_PROJECT);
    }
    catch (Exception localException5) {}
    ArrayList arrPro = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.project.select"));
    value.setValue("0");
    arrPro.add(value);
    for (int i = 0; i < proList.size(); i++)
    {
      OrganizationBean pro = (OrganizationBean)proList.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(StringUtil.decodeString(pro.getName())));
      value.setValue(String.valueOf(pro.getOrgId()));
      arrPro.add(value);
    }
    request.setAttribute("projectList", arrPro);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_MIV;
  }
}
