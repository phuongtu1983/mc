package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MrirDAO mrirDAO = new MrirDAO();
    ArrayList mrirList = null;
    try
    {
      mrirList = mrirDAO.getMrirs();
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Mrir:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("mrirList", mrirList);
    return true;
  }
}
