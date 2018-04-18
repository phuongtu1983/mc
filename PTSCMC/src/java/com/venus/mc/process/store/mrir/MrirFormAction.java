package com.venus.mc.process.store.mrir;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.request.RequestFormBean;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MrirFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    DnDAO dnDAO = new DnDAO();
    MrirDAO mrirDAO = new MrirDAO();
    MrirFormBean formBean = null;
    
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      mrirId = id.intValue();
    }
    if (mrirId > 0)
    {
      MrirBean bean = null;
      try
      {
        bean = mrirDAO.getMrir(mrirId);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      formBean = new MrirFormBean(bean);
      request.setAttribute("mrir", formBean);
      
      ArrayList arrMat = null;
      try
      {
        arrMat = mrirDAO.getMrirDetailsByMrir(mrirId);
      }
      catch (Exception ex)
      {
        LogUtil.error("MrirFormAction:" + ex.getMessage());
      }
      request.setAttribute("materialList", arrMat);
      
      ArrayList arrReq = null;
      try
      {
        boolean isWhichUse = true;
        StoreDAO stoDAO = new StoreDAO();
        int stoId = stoDAO.getStore1IdByPro(formBean.getProId());
        if (stoId == 1) {
          isWhichUse = false;
        }
        arrReq = dnDAO.getRequestListByDnId(bean.getDnId(), formBean.getProId(), isWhichUse);
      }
      catch (Exception ex)
      {
        LogUtil.error("MrirFormAction:" + ex.getMessage());
      }
      if ((arrReq != null) && (!arrReq.isEmpty()))
      {
        LabelValueBean labelSel = new LabelValueBean();
        labelSel.setLabel(MCUtil.getBundleString("message.select"));
        arrReq.add(0, labelSel);
        request.setAttribute("mrirRequestList", arrReq);
      }
      ArrayList arrDn = new ArrayList();
      LabelValueBean label = new LabelValueBean();
      label.setValue("" + formBean.getDnId());
      label.setLabel(formBean.getDnNumber());
      arrDn.add(0, label);
      request.setAttribute("mrirDeliveryNoticeList", arrDn);
    }
    else
    {
      int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
      int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
      int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
      if (kind == 1)
      {
        ArrayList arrPro = getProjectList();
        request.setAttribute("mrirProjectList", arrPro);
        if (proId > 0)
        {
          ArrayList arrDn = getDnListOfProject(proId, kind, MCUtil.getOrganizationID(request.getSession()));
          request.setAttribute("mrirDeliveryNoticeList", arrDn);
        }
      }
      else
      {
        ArrayList arrPro = getProjectList();
        request.setAttribute("mrirProjectList", arrPro);
        if (proId > 0)
        {
          ArrayList arrDn = getDnListOfProject(proId, kind, MCUtil.getOrganizationID(request.getSession()));
          request.setAttribute("mrirDeliveryNoticeList", arrDn);
        }
      }
      if (dnId > 0)
      {
        LabelValueBean labelSel = null;
        ArrayList arrReq = null;
        try
        {
          boolean isWhichUse = true;
          StoreDAO stoDAO = new StoreDAO();
          int stoId = stoDAO.getStore1IdByPro(proId);
          if (stoId == 1) {
            isWhichUse = false;
          }
          arrReq = dnDAO.getRequestListByDnId(dnId, proId, isWhichUse);
        }
        catch (Exception ex)
        {
          LogUtil.error("MrirFormAction:" + ex.getMessage());
        }
        ArrayList arrMat = null;
        DeliveryNoticeDetailBean dn = new DeliveryNoticeDetailBean();
        ArrayList arrMaterial = new ArrayList();
        if (arrReq != null) {
          if (arrReq.isEmpty())
          {
            try
            {
              arrMat = dnDAO.getDeliveryNoticeDetails(dnId, 0, 0);
            }
            catch (Exception ex)
            {
              LogUtil.error("MrirFormAction:" + ex.getMessage());
            }
          }
          else
          {
            labelSel = new LabelValueBean();
            labelSel.setLabel(MCUtil.getBundleString("message.select"));
            labelSel.setValue("0");
            arrReq.add(0, labelSel);
            request.setAttribute("mrirRequestList", arrReq);
          }
        }
        if (arrMat == null) {
          arrMat = new ArrayList();
        }
        labelSel = new LabelValueBean();
        labelSel.setLabel(MCUtil.getBundleString("message.select"));
        labelSel.setValue("0");
        arrMaterial.add(0, labelSel);
        if (arrMat != null) {
          for (int i = 0; i < arrMat.size(); i++)
          {
            dn = (DeliveryNoticeDetailBean)arrMat.get(i);
            LabelValueBean value1 = new LabelValueBean();
            value1.setLabel(dn.getMatName());
            value1.setValue(dn.getDetId() + "");
            arrMaterial.add(value1);
          }
        }
        request.setAttribute("mrirMaterialList", arrMaterial);
        try
        {
          formBean = dnDAO.createNewMrirFromDn(dnId);
        }
        catch (Exception ex)
        {
          LogUtil.error("MrirFormAction:" + ex.getMessage());
        }
      }
      if (formBean == null) {
        formBean = new MrirFormBean();
      }
      formBean.setDnId(dnId);
      formBean.setKind(kind);
      formBean.setProId(proId);
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      try
      {
        String prefix = "";
        if (proId == 0)
        {
          prefix = "CKHH-MRIR-XXX-YYYY";
        }
        else
        {
          prefix = "CKHH-MRIR-";
          OrganizationDAO orgDAO = new OrganizationDAO();
          OrganizationBean ob = orgDAO.getOrganization(proId);
          if (ob != null) {
            prefix = prefix + ob.getAbbreviate() + "-";
          } else {
            prefix = prefix + "XXX-";
          }
          String number = dnDAO.getNextMrirNumber(prefix, 4);
          prefix = prefix + number;
        }
        formBean.setMrirNumber(prefix);
      }
      catch (Exception localException1) {}
      request.setAttribute("mrir", formBean);
    }
    ArrayList arrMaterialKind = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.materialKind0"));
    value.setValue(RequestFormBean.KIND_BOTH + "");
    arrMaterialKind.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.materialKind1"));
    value.setValue(RequestFormBean.KIND_MAJOR + "");
    arrMaterialKind.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.mrir.materialKind2"));
    value.setValue(RequestFormBean.KIND_SECONDARY + "");
    arrMaterialKind.add(value);
    request.setAttribute("requestMaterialKindList", arrMaterialKind);
    
    return true;
  }
  
  protected ArrayList getProjectList()
  {
    OrganizationDAO orgDAO = new OrganizationDAO();
    ArrayList arrOrg = null;
    try
    {
      arrOrg = orgDAO.getProjectList();
    }
    catch (Exception ex)
    {
      LogUtil.error("MrirFormAction:" + ex.getMessage());
    }
    if (arrOrg != null)
    {
      LabelValueBean label = new LabelValueBean();
      label.setValue("0");
      label.setLabel(MCUtil.getBundleString("message.select"));
      arrOrg.add(0, label);
    }
    return arrOrg;
  }
  
  protected ArrayList getDnListOfCompany(int kind)
  {
    DnDAO dnDAO = new DnDAO();
    ArrayList arrDn = null;
    try
    {
      arrDn = dnDAO.getDnListForMrirOfCompany(0, kind);
    }
    catch (Exception ex)
    {
      LogUtil.error("MrirFormAction:" + ex.getMessage());
    }
    LabelValueBean label = new LabelValueBean();
    label.setValue("0");
    label.setLabel(MCUtil.getBundleString("message.select"));
    arrDn.add(0, label);
    return arrDn;
  }
  
  protected ArrayList getDnListOfProject(int proId, int kind, int orgId)
  {
    DnDAO dnDAO = new DnDAO();
    ArrayList arrDn = null;
    try
    {
      StoreDAO stoDAO = new StoreDAO();
      boolean isWhichUse = true;
      int stoId = stoDAO.getStore1IdByPro(proId);
      if (stoId == 1) {
        isWhichUse = false;
      }
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgDAO.getSiblingOrganization(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      arrDn = dnDAO.getDnListForMrirOfProject(0, proId, kind, isWhichUse, orgs);
    }
    catch (Exception ex)
    {
      LogUtil.error("MrirFormAction:" + ex.getMessage());
    }
    if (arrDn != null)
    {
      LabelValueBean label = new LabelValueBean();
      label.setValue("0");
      label.setLabel(MCUtil.getBundleString("message.select"));
      arrDn.add(0, label);
    }
    return arrDn;
  }
  
  protected String getXmlMessage()
  {
    return "mrirForm";
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("mrirId"))) {
      return request.getParameter("mrirId");
    }
    return "";
  }
}
