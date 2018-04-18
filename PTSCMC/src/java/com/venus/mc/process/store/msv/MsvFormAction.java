package com.venus.mc.process.store.msv;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MsvFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    MsvFormBean formBean = null;
    String msvId = request.getParameter("msvId");
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      msvId = id + "";
    }
    if (!GenericValidator.isBlankOrNull(msvId))
    {
      MsvDAO msvDAO = new MsvDAO();
      try
      {
        MsvBean bean = msvDAO.getMsv(Integer.parseInt(msvId));
        if (bean != null) {
          formBean = new MsvFormBean(bean);
        }
      }
      catch (Exception ex)
      {
        LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
      }
      ArrayList arrMaterial = null;
      try
      {
        arrMaterial = msvDAO.getMaterialsFromMsv(Integer.parseInt(msvId));
      }
      catch (Exception ex)
      {
        LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
      }
      request.setAttribute("materialList", arrMaterial);
    }
    else
    {
      int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
      int type = NumberUtil.parseInt(request.getParameter("type"), 0);
      MsvBean bean = null;
      MsvDAO msvDAO = new MsvDAO();
      if (mrirId > 0)
      {
        try
        {
          bean = msvDAO.getNewMsvFromMrir(type, mrirId);
        }
        catch (Exception ex)
        {
          LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
        }
        MrirDAO mrirDAO = new MrirDAO();
        ArrayList arrStore = null;
        try
        {
          arrStore = mrirDAO.getStoresOfMrir(mrirId, 1);
        }
        catch (Exception ex)
        {
          LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
        }
        if (arrStore == null) {
          arrStore = new ArrayList();
        }
        request.setAttribute("msvStoreList", arrStore);
        
        int stoId = 0;
        if ((bean != null) && (type != 1)) {
          stoId = bean.getStoId();
        }
        ArrayList arrMaterial = null;
        try
        {
          arrMaterial = msvDAO.getMaterialsFromMrir(mrirId, stoId);
        }
        catch (Exception ex)
        {
          LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
        }
        request.setAttribute("materialList", arrMaterial);
      }
      formBean = new MsvFormBean();
      formBean.setMrirId(mrirId);
      formBean.setType(type);
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
      try
      {
        String prefix = "";
        if (mrirId == 0)
        {
          prefix = "CKHH-MSV-XXX-YYYY";
        }
        else
        {
          prefix = "CKHH-MSV-";
          if (type == 1) {
            prefix = "CKHH-MRV-";
          }
          StoreDAO stoDAO = new StoreDAO();
          StoreBean sb = stoDAO.getStore(bean.getStoId());
          if (sb != null)
          {
            OrganizationDAO orgDAO = new OrganizationDAO();
            OrganizationBean ob = orgDAO.getOrganization(sb.getProId());
            if (ob != null) {
              prefix = prefix + ob.getAbbreviate() + "-";
            } else {
              prefix = prefix + "XXX-";
            }
            String number = msvDAO.getNextMsvNumber(prefix, 4);
            prefix = prefix + number;
          }
        }
        formBean.setMsvNumber(prefix);
      }
      catch (Exception localException1) {}
      if (bean != null)
      {
        formBean.setStoId(bean.getStoId());
        formBean.setStoName(bean.getStoName());
        
        formBean.setProId(bean.getProId());
        formBean.setProName(bean.getProName());
        formBean.setConNumber(bean.getConNumber());
        formBean.setVendorName(bean.getVendorName());
        formBean.setOrgId(bean.getOrgId());
        formBean.setOrgName(bean.getOrgName());
      }
      ArrayList arrMrir = null;
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        arrMrir = mrirDAO.getMrir4Msv(type);
      }
      catch (Exception ex)
      {
        LogUtil.error(MsvFormAction.class.getName() + ": " + ex.getMessage());
      }
      if (arrMrir == null) {
        arrMrir = new ArrayList();
      }
      arrMrir.add(0, new LabelValueBean(MCUtil.getBundleString("message.msv.mrirsel"), "0"));
      request.setAttribute("mrirList", arrMrir);
    }
    if (formBean == null)
    {
      formBean = new MsvFormBean();
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreatedEmpId(MCUtil.getMemberID(request.getSession()));
    }
    ArrayList arrType = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dmv.tscdhh"));
    value.setValue("2");
    arrType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dmv.ccdc"));
    value.setValue("1");
    arrType.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.dmv.khac"));
    value.setValue("0");
    arrType.add(value);
    request.setAttribute("msvKindList", arrType);
    if (formBean.getType() == 1)
    {
      request.setAttribute("mrvObj", formBean);
      
      EmployeeDAO empDAO = new EmployeeDAO();
      try
      {
        ArrayList empList = empDAO.getEmployeeListByOrg(formBean.getOrgId());
        empList.add(0, new LabelValueBean(MCUtil.getBundleString("message.employee.select"), "0"));
        request.setAttribute("empList", empList);
      }
      catch (Exception localException2) {}
      this.actionForwardResult = "mrvsuccess";
    }
    else if (formBean.getType() == 2)
    {
      request.setAttribute("dmvObj", formBean);
      
      EmployeeDAO empDAO = new EmployeeDAO();
      try
      {
        ArrayList empList = empDAO.getEmployeeListByOrg(formBean.getOrgId());
        empList.add(0, new LabelValueBean(MCUtil.getBundleString("message.select"), "0"));
        request.setAttribute("empList", empList);
      }
      catch (Exception localException3) {}
      this.actionForwardResult = "dmvsuccess";
    }
    else
    {
      request.setAttribute("msvObj", formBean);
    }
    return true;
  }
  
  protected String getXmlMessage()
  {
    return "msvForm";
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("msvId"))) {
      return request.getParameter("msvId");
    }
    return "";
  }
}
