package com.venus.mc.material;

import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String matId = request.getParameter("matId");
    try
    {
      MaterialDAO materialDAO = new MaterialDAO();
      MaterialBean bean = materialDAO.getMaterial(matId);
      String json = "{\"matCode\":\"" + bean.getCode() + "\"";
      json = json + ",\"matName\":\"" + bean.getNameVn() + "\"";
      json = json + ",\"matUnit\":\"" + bean.getUnitName() + "\"";
      json = json + "}";
      OutputUtil.sendStringToOutput(response, json);
    }
    catch (Exception localException) {}
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
