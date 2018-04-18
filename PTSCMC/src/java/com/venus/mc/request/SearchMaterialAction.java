package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    int fieldid = formBean.getSearchid();
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    try
    {
      MaterialDAO materialDAO = new MaterialDAO();
      String reqDetId = request.getParameter("reqDetId");
      if (GenericValidator.isBlankOrNull(reqDetId)) {
        reqDetId = "0";
      }
      materialList = materialDAO.searchMaterialNotCode(fieldid, StringUtil.encodeHTML(strFieldvalue), reqDetId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Material:search-" + ex.getMessage());
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
