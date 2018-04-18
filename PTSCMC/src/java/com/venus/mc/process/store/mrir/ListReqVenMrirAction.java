package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.DeliveryNoticeBean;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListReqVenMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    
    MrirFormBean formBean = null;
    try
    {
      MrirDAO mrirDAO = new MrirDAO();
      MrirBean mrirBean = mrirDAO.getMrir(mrirId);
      if (mrirBean != null) {
        formBean = new MrirFormBean(mrirBean);
      }
    }
    catch (Exception localException) {}
    if (formBean == null)
    {
      formBean = new MrirFormBean();
      formBean.setDnId(dnId);
    }
    DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
    DeliveryNoticeBean dnBean = null;
    ContractDAO conDAO = new ContractDAO();
    VendorDAO venDAO = new VendorDAO();
    VendorBean venBean = null;
    RequestDAO reqDAO = new RequestDAO();
    ArrayList arrReq = new ArrayList();
    try
    {
      dnBean = dnDAO.getDeliveryNotice(dnId);
      if (dnBean.getConId() > 0)
      {
        ContractBean conBean = conDAO.getContractMrir(dnBean.getConId());
        venBean = venDAO.getVendor(conBean.getVenId());
        if (venBean == null) {
          formBean.setVenName("");
        } else {
          formBean.setVenName(conBean.getContractNumber() + "/" + venBean.getName());
        }
        arrReq = reqDAO.getRequestsByDnId(dnBean.getDnId());
      }
    }
    catch (Exception localException1) {}
    request.setAttribute("mrir", formBean);
    if (arrReq == null) {
      arrReq = new ArrayList();
    }
    request.setAttribute("mrirRequestList", arrReq);
    this.actionForwardResult = "reqvens";
    return true;
  }
}
