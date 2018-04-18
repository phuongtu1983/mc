package com.venus.mc.process.store.miv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleMivAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean mivForm = (SearchFormBean)form;
    int fieldid = mivForm.getSearchid();
    String strFieldvalue = mivForm.getSearchvalue();
    String storeId = mivForm.getSearchvalues();
    ArrayList mivList = null;
    MivDAO mivDAO = new MivDAO();
    try
    {
      mivList = mivDAO.searchSimpleMiv(fieldid, StringUtil.encodeHTML(strFieldvalue), storeId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Miv:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("mivList", mivList);
    return true;
  }
}
