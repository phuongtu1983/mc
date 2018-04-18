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

public class SearchSimpleMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean mrirForm = (SearchFormBean)form;
    int fieldid = mrirForm.getSearchid();
    String strFieldvalue = mrirForm.getSearchvalue();
    String strFieldvalues = mrirForm.getSearchvalues();
    ArrayList mrirList = null;
    MrirDAO mrirDAO = new MrirDAO();
    try
    {
      mrirList = mrirDAO.searchSimpleMrirProject(fieldid, StringUtil.encodeHTML(strFieldvalue), strFieldvalues);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED: MrirBean:searchSimpleMrir-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("mrirList", mrirList);
    
    return true;
  }
}
