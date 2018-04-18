package com.venus.mc.process.store.mrv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MsvDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleMrvAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean mrirForm = (SearchFormBean)form;
    int fieldid = mrirForm.getSearchid();
    String strFieldvalue = mrirForm.getSearchvalue();
    ArrayList mrvList = null;
    MsvDAO msvDAO = new MsvDAO();
    try
    {
      mrvList = msvDAO.searchSimple(1, fieldid, StringUtil.encodeHTML(strFieldvalue));
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED: MsvBean:searchSimpleMsv-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("mrvList", mrvList);
    return true;
  }
}
