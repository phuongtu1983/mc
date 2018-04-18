package com.venus.mc.request;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class CheckMaterialFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
    try
    {
      RequestDAO reqDAO = new RequestDAO();
      RequestBean reqBean = reqDAO.getRequest(reqId);
      if (reqBean == null) {
        reqBean = new RequestBean();
      }
      StoreDAO stoDAO = new StoreDAO();
      StoreBean stoBean = null;
      int stoId = 1;
      if (reqBean.getProId() > 0)
      {
        stoBean = stoDAO.getProjectStore(reqBean.getProId());
        if (stoBean == null) {
          stoBean = new StoreBean();
        }
        stoId = stoBean.getStoId();
      }
      reqBean.setOrgId(stoId);
      request.setAttribute("request", reqBean);
      ArrayList materialList = reqDAO.getRequestDetails(reqId);
      RequestDetailFormBean request_detail = new RequestDetailFormBean();
      request_detail.setMatName(MCUtil.getBundleString("message.all"));
      materialList.add(0, request_detail);
      request.setAttribute("materialList", materialList);
      
      ArrayList arrStore = stoDAO.getStoresLevel1();
      if (arrStore != null)
      {
        LabelValueBean label = new LabelValueBean();
        label.setValue("all");
        label.setLabel(MCUtil.getBundleString("message.all"));
        arrStore.add(0, label);
      }
      request.setAttribute("storeList", arrStore);
    }
    catch (Exception localException) {}
    return true;
  }
}
