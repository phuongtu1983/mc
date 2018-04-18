package com.venus.mc.material;

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

public class SearchSimpleMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean materialForm = (SearchFormBean)form;
    int fieldid = materialForm.getSearchid();
    String strFieldvalue = materialForm.getSearchvalue();
    ArrayList materialList = null;
    MaterialDAO materialDAO = new MaterialDAO();
    try
    {
      materialList = materialDAO.searchSimpleMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue), 1);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED: MaterialBean:searchSimpleMaterial-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
