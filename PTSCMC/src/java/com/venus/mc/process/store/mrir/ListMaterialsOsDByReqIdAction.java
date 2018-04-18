package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListMaterialsOsDByReqIdAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
    int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
    int materialKind = NumberUtil.parseInt(request.getParameter("materialKind"), 0);
    ArrayList arrMat = null;
    DnDAO dnDAO = new DnDAO();
    if ((reqId > 0) && (dnId > 0)) {
      try
      {
        arrMat = dnDAO.getDeliveryNoticeDetailsRemainQuantityOsD(dnId, reqId, materialKind);
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
    }
    if (arrMat == null) {
      arrMat = new ArrayList();
    }
    LabelValueBean labelSel = new LabelValueBean();
    labelSel.setLabel(MCUtil.getBundleString("message.select"));
    arrMat.add(0, labelSel);
    request.setAttribute("mrirMaterialList", arrMat);
    
    return true;
  }
}
