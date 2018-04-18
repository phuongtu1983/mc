package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddRequestCopyAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    RequestFormBean formBean = (RequestFormBean)form;
    RequestDAO requestDAO = new RequestDAO();
    int reqId = formBean.getReqId();
    try
    {
      String appSuggest = "";
      if (formBean.getApproveSuggest() != null)
      {
        int length = formBean.getApproveSuggest().length;
        appSuggest = formBean.getApproveSuggest()[0];
        for (int i = 1; i < length; i++) {
          appSuggest = appSuggest + "," + formBean.getApproveSuggest()[i];
        }
      }
      reqId = requestDAO.copyRequest(reqId, appSuggest, formBean);
      session.setAttribute("id", Integer.valueOf(reqId));
      if (reqId != 0)
      {
        String[] detIds = formBean.getReqDetId();
        String[] matIds = formBean.getMatId();
        double additionQuantity = 0.0D;
        double requestQuantity = 0.0D;
        RequestDetailBean detBean = null;
        int detId = 0;
        for (int i = 0; i < detIds.length; i++) {
          try
          {
            if (GenericValidator.isBlankOrNull(formBean.getAdditionalQuantity()[i])) {
              additionQuantity = 0.0D;
            } else {
              additionQuantity = Double.parseDouble(formBean.getAdditionalQuantity()[i]);
            }
            if (GenericValidator.isBlankOrNull(formBean.getRequestQuantity()[i])) {
              requestQuantity = 0.0D;
            } else {
              requestQuantity = Double.parseDouble(formBean.getRequestQuantity()[i]);
            }
            detId = NumberUtil.parseInt(formBean.getReqDetId()[i], 0);
            detBean = new RequestDetailBean(detId, formBean.getUnit()[i], Double.parseDouble(formBean.getPresentQuantity()[i]), additionQuantity, requestQuantity, formBean.getDeliverDate()[i], 0, 0, 0, 0, "");
            
            detBean.setReqId(reqId);
            detBean.setMaterialKind(RequestFormBean.KIND_SECONDARY);
            detBean.setMatId(Integer.parseInt(matIds[i]));
            if (detId > 0) {
              requestDAO.insertRequestCopyDetail(detBean);
            } else {
              requestDAO.insertRequestDetail(detBean);
            }
          }
          catch (Exception localException1) {}
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestCopy:add-" + ex.getMessage());
    }
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
