package com.venus.mc.process.store.msv;

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

public class SearchSimpleMsvAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean mrirForm = (SearchFormBean)form;
    int fieldid = mrirForm.getSearchid();
    String strFieldvalue = mrirForm.getSearchvalue();
    String storeId = mrirForm.getSearchvalues();
    ArrayList msvList = null;
    MsvDAO msvDAO = new MsvDAO();
    try
    {
      msvList = msvDAO.searchSimple(0, fieldid, StringUtil.encodeHTML(strFieldvalue), storeId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED: MsvBean:searchSimpleMsv-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("msvList", msvList);
    return true;
  }
}
