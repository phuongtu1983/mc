package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMivAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MivDAO mivDAO = new MivDAO();
    mivDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList mivList = null;
    try
    {
      int kind = 0;
      if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
        kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
      }
      if (kind == MivBean.KIND_COMPANY) {
        mivList = mivDAO.getMivs();
      } else {
        mivList = mivDAO.getEMivs();
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Miv:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("mivList", mivList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_MIV;
  }
}
