package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
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
      MrirDAO mrirDAO = new MrirDAO();
      if (fieldid > 0) {
        materialList = mrirDAO.searchMaterials(fieldid, StringUtil.encodeHTML(strFieldvalue));
      } else {
        materialList = mrirDAO.getMaterials();
      }
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
