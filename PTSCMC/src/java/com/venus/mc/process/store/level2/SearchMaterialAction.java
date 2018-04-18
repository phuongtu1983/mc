package com.venus.mc.process.store.level2;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
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
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    int fieldid = formBean.getSearchid();
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    try
    {
      StoreDAO storeDAO = new StoreDAO();
      materialList = storeDAO.searchStoreLevel2Material(stoId, fieldid, StringUtil.encodeHTML(strFieldvalue), formBean.getExtraSearchId(), formBean.getExtraSearchValue());
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:UsedMaterial:search-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
