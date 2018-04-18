package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RequestHandledBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChangeRequestStatusAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String reqId = request.getParameter("reqId");
    String status = request.getParameter("status");
    String orgHandle = request.getParameter("orgHandle");
    if (GenericValidator.isBlankOrNull(orgHandle)) {
      orgHandle = "4";
    }
    RequestDAO requestDAO = new RequestDAO();
    RequestBean bean = null;
    try
    {
      bean = requestDAO.getRequest(Integer.parseInt(reqId));
      int s = Integer.parseInt(status);
      if (s == RequestBean.STATUS_MANAGER)
      {
        bean.setCheckApprove(1);
        bean.setCheckComment("");
        bean.setPlandepApprove(1);
        bean.setPlandepComment("");
      }
      else if (s == RequestBean.STATUS_HANDLE)
      {
        insertRequestHandledOrg(bean.getReqId(), orgHandle);
      }
      bean.setStatus(s);
      requestDAO.updateRequest(bean);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:changRequest-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private void insertRequestHandledOrg(int reqId, String orgHandle)
  {
    try
    {
      RequestDAO requestDAO = new RequestDAO();
      ArrayList arrDetail = requestDAO.getRequestOrgHandle(reqId);
      String[] orgs = orgHandle.split(",");
      int length = orgs.length;
      int id = 0;
      for (int i = 0; i < length; i++)
      {
        id = NumberUtil.parseInt(orgs[i], 0);
        boolean isNew = true;
        for (int j = 0; j < arrDetail.size(); j++)
        {
          RequestHandledBean bean = (RequestHandledBean)arrDetail.get(j);
          if (bean.getOrgId() == id) {
            isNew = false;
          }
        }
        if (isNew)
        {
          RequestHandledBean detBean = new RequestHandledBean();
          detBean.setReqId(reqId);
          detBean.setOrgId(NumberUtil.parseInt(orgs[i], 0));
          requestDAO.insertRequestHandle(detBean);
        }
        else
        {
          int j = 0;
          RequestHandledBean oldBean = null;
          for (; j < arrDetail.size(); j++)
          {
            oldBean = (RequestHandledBean)arrDetail.get(j);
            if (oldBean.getId() == id) {
              break;
            }
          }
          if (j < arrDetail.size()) {
            oldBean = (RequestHandledBean)arrDetail.remove(j);
          }
        }
      }
      String ids = "0";
      RequestHandledBean oldBean = null;
      for (int i = 0; i < arrDetail.size(); i++)
      {
        oldBean = (RequestHandledBean)arrDetail.get(i);
        ids = ids + "," + oldBean.getId();
      }
      if (!ids.equals("0")) {
        requestDAO.deleteRequestHandled(ids.substring(2));
      }
    }
    catch (Exception localException) {}
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
