package com.venus.mc.process.store.rfm;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.request.RequestFormBean;
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

public class RfmFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    RfmBean bean = null;
    String rfmId = request.getParameter("rfmId");
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      rfmId = id + "";
    }
    if (!GenericValidator.isBlankOrNull(rfmId))
    {
      RfmDAO rfmDAO = new RfmDAO();
      ArrayList arrRequestDetail = null;
      try
      {
        bean = rfmDAO.getRfm(NumberUtil.parseInt(rfmId, 0), kind, MCUtil.getMemberID(request.getSession()));
        arrRequestDetail = rfmDAO.getRfmDetails(Integer.parseInt(rfmId), kind);
      }
      catch (Exception localException) {}
      if (arrRequestDetail == null) {
        arrRequestDetail = new ArrayList();
      }
      request.setAttribute("rfmDetailList", arrRequestDetail);
    }
    if (bean == null)
    {
      bean = new RfmBean();
      bean.setEmpName(MCUtil.getMemberFullName(session));
      bean.setCreator(MCUtil.getMemberID(session));
      bean.setOrgName(MCUtil.getOrganizationName(session));
      bean.setKind(kind);
      bean.setReceiveId(1);
      bean.setReqType(1);
      bean.setOrgHandle(4);
      bean.setStoId(stoId);
      bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      bean.setDeliveryDate(DateUtil.today("dd/MM/yyyy"));
      bean.setRfmNumber("CKHH-YCXK-XXX-YYYY");
      bean.setCanSave(1);
    }
    request.setAttribute("rfm", bean);
    
    ArrayList orgList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      orgList = orgDAO.getOrgByDN();
    }
    catch (Exception localException1) {}
    ArrayList arrOrg = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
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
    
    orgList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      orgList = orgDAO.getOrgByDN();
    }
    catch (Exception localException2) {}
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
      OrganizationDAO orgDAO = new OrganizationDAO();
      proList = orgDAO.getOrgByKind(OrganizationBean.KIND_PROJECT);
    }
    catch (Exception localException3) {}
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
    
    ArrayList arrSto = new ArrayList();
    if (stoId > 0)
    {
      StoreBean sBean = null;
      try
      {
        StoreDAO stoDAO = new StoreDAO();
        sBean = stoDAO.getStore(stoId);
      }
      catch (Exception localException4) {}
      if (sBean == null) {
        sBean = new StoreBean();
      }
      value = new LabelValueBean(sBean.getName(), sBean.getStoId() + "");
      arrSto.add(value);
    }
    else
    {
      ArrayList stoList = null;
      try
      {
        StoreDAO stoDAO = new StoreDAO();
        stoList = stoDAO.getStores(1);
      }
      catch (Exception localException5) {}
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.store.select"));
      value.setValue("0");
      arrSto.add(value);
      for (int i = 0; i < stoList.size(); i++)
      {
        StoreBean sto = (StoreBean)stoList.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(StringUtil.decodeString(sto.getName())));
        value.setValue(String.valueOf(sto.getStoId()));
        arrSto.add(value);
      }
    }
    request.setAttribute("storeList", arrSto);
    
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
    request.setAttribute("accountingApproveList", arrApprove);
    request.setAttribute("storeApproveList", arrApprove);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_RFM;
  }
}
