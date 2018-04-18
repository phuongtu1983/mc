package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListWhichUseMivAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String whichUse = request.getParameter("whichUse");
    int orgId = NumberUtil.parseInt(request.getParameter("orgId"), 0);
    int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    if (GenericValidator.isBlankOrNull(whichUse)) {
      return true;
    }
    int iWhichUse = NumberUtil.parseInt(whichUse, 0);
    
    ArrayList arrList = null;
    if (orgId > 1)
    {
      RfmDAO rfmDAO = new RfmDAO();
      try
      {
        if (kind == MivBean.KIND_COMPANY) {
          arrList = rfmDAO.getRfmsAvailable(proId, orgId);
        } else {
          arrList = rfmDAO.getERfmsAvailable();
        }
        request.setAttribute("dnWhichUse", arrList);
        this.actionForwardResult = "rfms";
      }
      catch (Exception localException) {}
    }
    String mivId = request.getParameter("mivId");
    MivFormBean formBean = null;
    
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
      catch (Exception localException1) {}
    }
    if (formBean == null)
    {
      formBean = new MivFormBean();
      formBean.setCreatorName(MCUtil.getMemberFullName(request.getSession()));
      formBean.setCreator(MCUtil.getMemberID(request.getSession()));
      formBean.setMivKind(kind);
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
      catch (Exception localException2) {}
      if (arrDetail != null) {
        request.setAttribute("mivDetailList", arrDetail);
      }
    }
    request.setAttribute("miv", formBean);
    
    return true;
  }
}
