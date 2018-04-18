package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    int fieldid = formBean.getSearchid();
    int whichUse = NumberUtil.parseInt(request.getParameter("whichUse"), 0);
    int conId = NumberUtil.parseInt(request.getParameter("conId"), 0);
    int drId = NumberUtil.parseInt(request.getParameter("drId"), 0);
    int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    HttpSession session = request.getSession();
    int orgId = MCUtil.getOrganizationID(session);
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    try
    {
      DnDAO materialDAO = new DnDAO();
      ContractDAO conDAO = new ContractDAO();
      int id = 0;
      id = conDAO.getContractId(conId + "");
      if (proId == 0)
      {
        if (id > 0) {
          materialList = materialDAO.searchDnMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue), whichUse, id, drId);
        } else {
          materialList = materialDAO.searchDnMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue), whichUse, conId, drId);
        }
      }
      else if (kind == 2)
      {
        DnDAO dnDAO = new DnDAO();
        materialList = dnDAO.getMaterialsOfProject(proId, orgId, fieldid, StringUtil.encodeHTML(strFieldvalue));
      }
      else
      {
        materialList = materialDAO.searchMaterial(fieldid, StringUtil.encodeHTML(strFieldvalue));
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
