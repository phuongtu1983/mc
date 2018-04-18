package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OsDDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OsDMaterialFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    OsDMaterialFormBean formBean = null;
    OsDDetailBean bean = null;
    if (detId > 0)
    {
      MrirDAO osDDAO = new MrirDAO();
      try
      {
        bean = osDDAO.getOsDMaterial(detId);
        if (bean != null) {
          formBean = new OsDMaterialFormBean(bean);
        }
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
    }
    if (formBean == null) {
      formBean = new OsDMaterialFormBean();
    }
    request.setAttribute("osDMaterial", formBean);
    return true;
  }
}
