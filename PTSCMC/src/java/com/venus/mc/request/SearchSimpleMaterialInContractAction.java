package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleMaterialInContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean vendorForm = (SearchFormBean)form;
    int fieldid = vendorForm.getSearchid();
    String strFieldvalue = vendorForm.getSearchvalue();
    ArrayList requestList = null;
    RequestDAO requestDAO = new RequestDAO();
    try
    {
      requestList = requestDAO.searchSimpleRequest(fieldid, StringUtil.encodeHTML(strFieldvalue), 0);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("requestList", requestList);
    return true;
  }
}
