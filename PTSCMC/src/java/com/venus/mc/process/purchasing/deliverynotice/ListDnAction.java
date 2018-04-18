package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDnAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 1);
    DnDAO dnDAO = new DnDAO();
    ArrayList dnList = null;
    try
    {
      if (kind == DnBean.KIND1) {
        dnList = dnDAO.getDnsOfMsv(kind);
      }
      if (kind == DnBean.KIND2) {
        dnList = dnDAO.getDnsKind(kind);
      }
      if (kind == DnBean.KIND4) {
        dnList = dnDAO.getDnsKind(kind);
      }
      if (kind == DnBean.KIND5) {
        dnList = dnDAO.getDnsKind(kind);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Dn:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("dnList", dnList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
}
