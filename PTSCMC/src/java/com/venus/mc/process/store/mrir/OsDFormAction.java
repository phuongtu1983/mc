package com.venus.mc.process.store.mrir;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class OsDFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {}
    OsDFormBean formBean = null;
    MrirBean mrirBean = null;
    MrirDAO mrirDAO = new MrirDAO();
    OsDBean bean = null;
    try
    {
      bean = mrirDAO.getOsDByMrir(mrirId);
    }
    catch (Exception ex)
    {
      LogUtil.error("OsDFormAction: " + ex.getMessage());
    }
    if (bean == null)
    {
      formBean = new OsDFormBean();
      formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
      formBean.setMrirId(mrirId);
      formBean.setCreatedEmpId(MCUtil.getMemberID(session));
      formBean.setCreatedEmpName(MCUtil.getMemberFullName(session));
    }
    else
    {
      formBean = new OsDFormBean(bean);
      ArrayList arrMat = null;
      try
      {
        arrMat = mrirDAO.getOsDDetailsByOsD(bean.getOsdId());
      }
      catch (Exception ex)
      {
        LogUtil.error("MrirFormAction:" + ex.getMessage());
      }
      request.setAttribute("materialList", arrMat);
      try
      {
        String matIds = mrirDAO.getMaterialForEmailNon(formBean.getOsdId());
        if (!matIds.equals("0")) {
          request.setAttribute("canEmail", "true");
        }
      }
      catch (Exception localException1) {}
    }
    try
    {
      mrirBean = mrirDAO.getMrir(mrirId);
    }
    catch (Exception ex)
    {
      LogUtil.error("OsDFormAction: " + ex.getMessage());
    }
    if (mrirBean != null)
    {
      formBean.setConNumber(mrirBean.getConNumber());
      formBean.setVenName(mrirBean.getVenName());
      formBean.setDnId(mrirBean.getDnId());
      formBean.setDnNumber(mrirBean.getDnNumber());
    }
    request.setAttribute("osD", formBean);
    
    ArrayList arrEmp = null;
    EmployeeDAO empDAO = new EmployeeDAO();
    try
    {
      arrEmp = empDAO.getEmployees();
    }
    catch (Exception ex)
    {
      LogUtil.error("OsDFormAction: " + ex.getMessage());
    }
    if (arrEmp == null) {
      arrEmp = new ArrayList();
    }
    request.setAttribute("osDEmployeeList", arrEmp);
    
    ArrayList arrReq = null;
    DnDAO dnDAO = new DnDAO();
    try
    {
      boolean isWhichUse = true;
      StoreDAO stoDAO = new StoreDAO();
      int stoId = stoDAO.getStore1IdByPro(mrirBean.getProId());
      if (stoId == 1) {
        isWhichUse = false;
      }
      arrReq = dnDAO.getRequestListByDnId(mrirBean.getDnId(), mrirBean.getProId(), isWhichUse);
    }
    catch (Exception ex)
    {
      LogUtil.error("MrirFormAction:" + ex.getMessage());
    }
    ArrayList arrMatOfReq = null;
    LabelValueBean labelSel = null;
    if (arrReq != null) {
      if (arrReq.isEmpty())
      {
        try
        {
          arrMatOfReq = dnDAO.getDeliveryNoticeDetails(mrirBean.getDnId(), 0, 0);
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
        arrReq.add(0, labelSel);
        request.setAttribute("mrirRequestList", arrReq);
      }
    }
    if (arrMatOfReq == null) {
      arrMatOfReq = new ArrayList();
    }
    labelSel = new LabelValueBean();
    labelSel.setLabel(MCUtil.getBundleString("message.select"));
    arrMatOfReq.add(0, labelSel);
    request.setAttribute("osDMaterialList", arrMatOfReq);
    
    return true;
  }
}
