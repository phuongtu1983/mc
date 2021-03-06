package com.venus.mc.process.store.rfm;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
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
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    try
    {
      RfmDAO materialDAO = new RfmDAO();
      materialList = materialDAO.searchRfmMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue), kind, stoId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Material:search-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
