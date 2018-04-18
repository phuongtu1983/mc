package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.DnBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.deliveryrequest.DeliveryRequestFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListWhichUseDnAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String whichUse = request.getParameter("whichUse");
    String conId = request.getParameter("conId");
    if (GenericValidator.isBlankOrNull(whichUse)) {
      return true;
    }
    ContractFormBean conFormBean = null;
    if (!GenericValidator.isBlankOrNull(conId))
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        ContractBean conBean = conDAO.getContract(NumberUtil.parseInt(conId, 0));
        conFormBean = new ContractFormBean(conBean);
      }
      catch (Exception localException) {}
    }
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(conId))
    {
      ContractDAO conDAO = new ContractDAO();
      try
      {
        kind = conDAO.getContractKind(NumberUtil.parseInt(conId, 0));
      }
      catch (Exception localException1) {}
    }
    int iWhichUse = NumberUtil.parseInt(whichUse, 0);
    ArrayList arrList = null;
    ContractDAO conDAO = new ContractDAO();
    conDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
    String searchValue = request.getParameter("searchValue");
    if (iWhichUse == 1)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getContractsByDn1(1, conDAO.getConIds(1), searchValue);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException2) {}
    }
    else if (iWhichUse == 2)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getContractsByDn1(3, searchValue);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException3) {}
    }
    else if (iWhichUse == 6)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getContractsByDn1(8, searchValue);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException4) {}
    }
    else if (iWhichUse == 3)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getContractsByDn1(4, searchValue);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException5) {}
    }
    else if (iWhichUse == 4)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getDeliveryRequestsbyDn(searchValue);
        this.actionForwardResult = "deliverys";
      }
      catch (Exception localException6) {}
    }
    else if (iWhichUse == 5)
    {
      conDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      try
      {
        arrList = conDAO.getSearchContract(2, searchValue);
        this.actionForwardResult = "contracts";
      }
      catch (Exception localException7) {}
    }
    else
    {
      arrList = new ArrayList();
      this.actionForwardResult = "contracts";
    }
    if (arrList == null) {
      arrList = new ArrayList();
    }
    if (conFormBean != null)
    {
      int i = 0;
      Object b = null;
      for (i = 0; i < arrList.size(); i++)
      {
        b = arrList.get(i);
        if ((b instanceof ContractFormBean))
        {
          ContractFormBean c = (ContractFormBean)b;
          if (c.getConId() == conFormBean.getConId()) {
            break;
          }
        }
        else if ((b instanceof DeliveryRequestFormBean))
        {
          DeliveryRequestFormBean c = (DeliveryRequestFormBean)b;
          if (c.getDrId() == conFormBean.getConId()) {
            break;
          }
        }
      }
      if (i >= arrList.size()) {
        if (iWhichUse != 4)
        {
          arrList.add(0, conFormBean);
        }
        else
        {
          DeliveryRequestFormBean c = new DeliveryRequestFormBean();
          c.setDrId(conFormBean.getConId());
          c.setDeliveryNumber(conFormBean.getContractNumber());
          arrList.add(0, c);
        }
      }
    }
    request.setAttribute("dnWhichUse", arrList);
    String dnId = request.getParameter("dnId");
    DnBean bean = null;
    if (!GenericValidator.isBlankOrNull(dnId)) {
      try
      {
        DnDAO dnDAO = new DnDAO();
        bean = dnDAO.getDn(NumberUtil.parseInt(dnId, 0), DnBean.KIND1);
      }
      catch (Exception localException8) {}
    }
    if (bean == null)
    {
      bean = new DnBean();
      if (!GenericValidator.isBlankOrNull(conId)) {
        bean.setConId(Integer.parseInt(conId));
      }
    }
    request.setAttribute("dn", bean);
    return true;
  }
}
