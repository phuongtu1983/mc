package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListWhichUseMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String whichUse = request.getParameter("whichUse");
    if (GenericValidator.isBlankOrNull(whichUse)) {
      return true;
    }
    int iWhichUse = NumberUtil.parseInt(whichUse, 0);
    ArrayList arrList = null;
    if (iWhichUse == 1)
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        arrList = conDAO.getContracts(1);
        request.setAttribute("mrirWhichUse", arrList);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException) {}
    }
    else if (iWhichUse == 2)
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        arrList = conDAO.getContracts(3);
        request.setAttribute("mrirWhichUse", arrList);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException1) {}
    }
    else if (iWhichUse == 3)
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        arrList = conDAO.getContracts(4);
        request.setAttribute("mrirWhichUse", arrList);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException2) {}
    }
    else if (iWhichUse == 4)
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        arrList = conDAO.getDeliveryRequests();
        request.setAttribute("mrirWhichUse", arrList);
        this.actionForwardResult = "deliverys";
      }
      catch (Exception localException3) {}
    }
    else
    {
      arrList = new ArrayList();
      request.setAttribute("mrirWhichUse", arrList);
      this.actionForwardResult = "contracts";
    }
    if (arrList == null) {
      arrList = new ArrayList();
    }
    String mrirId = request.getParameter("mrirId");
    MrirFormBean formBean = null;
    if (!GenericValidator.isBlankOrNull(mrirId)) {
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        MrirBean bean = mrirDAO.getMrir(NumberUtil.parseInt(mrirId, 0));
        if (bean != null) {
          formBean = new MrirFormBean(bean);
        }
      }
      catch (Exception localException4) {}
    }
    if (formBean == null) {
      formBean = new MrirFormBean();
    }
    request.setAttribute("mrir", formBean);
    return true;
  }
}
