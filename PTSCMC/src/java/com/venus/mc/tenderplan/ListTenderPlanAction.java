package com.venus.mc.tenderplan;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListTenderPlanAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    tenderDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList tenderList = null;
    try
    {
      tenderList = tenderDAO.getTenderPlans();
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlan:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    TenderPlanBean tenderBean = null;
    long MILLSECS_PER_DAY = 86400000L;
    long delta = 0L;
    Date today = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "dd/MM/yyyy");
    for (int i = 0; i < tenderList.size(); i++)
    {
      tenderBean = (TenderPlanBean)tenderList.get(i);
      Date deadline = DateUtil.transformerStringEnDate(tenderBean.getBidDeadline(), "dd/MM/yyyy");
      if (deadline != null)
      {
        delta = deadline.getTime() - today.getTime();
        if (delta >= 0L)
        {
          delta /= MILLSECS_PER_DAY;
          if (delta <= 1L) {
            tenderBean.setIsNeedHighLight(1);
          }
        }
      }
    }
    request.setAttribute("tenderPlanList", tenderList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_TENDERPLAN;
  }
}
